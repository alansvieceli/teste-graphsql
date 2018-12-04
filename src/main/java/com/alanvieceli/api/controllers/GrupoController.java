package com.alanvieceli.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alanvieceli.api.dtos.GrupoDtoConsulta;
import com.alanvieceli.api.dtos.GrupoDtoPersistir;
import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.responses.Response;
import com.alanvieceli.api.services.GrupoService;
import com.alanvieceli.api.utils.Funcoes;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "*")
public class GrupoController {

	@Autowired
	private GrupoService serv;

	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	@GetMapping
	public ResponseEntity<Response<Page<GrupoDtoConsulta>>> listar(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir) {

		Response<Page<GrupoDtoConsulta>> response = new Response<Page<GrupoDtoConsulta>>();

		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);

		Page<Grupo> grupos = this.serv.listarTodos(pageRequest);
		if (grupos != null) {
			Page<GrupoDtoConsulta> gruposDto = grupos.map(grupo -> Funcoes.converterGrupoDtoConsulta(grupo));
			response.setData(gruposDto);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<GrupoDtoConsulta>> buscarPorId(@PathVariable("id") Long id) {
		Response<GrupoDtoConsulta> response = new Response<GrupoDtoConsulta>();
		Optional<Grupo> grupo = this.serv.buscarPorId(id);

		if (!grupo.isPresent()) {
			response.getErrors().add("Grupo não encontrado para o Id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(Funcoes.converterGrupoDtoConsulta(grupo.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<GrupoDtoConsulta>> cadastrar(@Valid @RequestBody GrupoDtoPersistir grupoDto,
			BindingResult result) {
		Response<GrupoDtoConsulta> response = new Response<GrupoDtoConsulta>();

		Grupo grupo = Funcoes.converterDtoPersistirGrupo(grupoDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Grupo grp = this.serv.salvar(grupo);

		response.setData(Funcoes.converterGrupoDtoConsulta(grp));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Grupo> grupo = this.serv.buscarPorId(id);

		if (!grupo.isPresent()) {
			response.getErrors().add("Erro ao remover grupo. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.serv.remover(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<GrupoDtoConsulta>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody GrupoDtoPersistir grupoDtoConsulta, BindingResult result) {

		Response<GrupoDtoConsulta> response = new Response<GrupoDtoConsulta>();
		Funcoes.validarGrupoDtoPersistir(serv, id, grupoDtoConsulta, result);

		Grupo grupo = null;
		if (!result.hasErrors()) {
			grupo = Funcoes.converterDtoPersistirGrupo(grupoDtoConsulta, result);
			grupo.setId(id);
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Grupo grp = this.serv.salvar(grupo);
		response.setData(Funcoes.converterGrupoDtoConsulta(grp));
		return ResponseEntity.ok(response);
	}

}
