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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alanvieceli.api.Response;
import com.alanvieceli.api.dtos.GrupoDto;
import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.services.GrupoService;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoService serv;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;	
	
	@GetMapping
	public ResponseEntity<Response<Page<GrupoDto>>> listar(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir){
		
		Response<Page<GrupoDto>> response = new Response<Page<GrupoDto>>();
		
		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Grupo> grupos = this.serv.listarTodos(pageRequest);
		Page<GrupoDto> gruposDto = grupos.map(grupo -> this.converterGrupoDto(grupo));
		response.setData(gruposDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping	
	public ResponseEntity<Response<GrupoDto>> cadastrar(@Valid @RequestBody GrupoDto grupoDto, BindingResult result){
		Response<GrupoDto> response = new Response<GrupoDto>();
		
		Grupo grupo = this.converterDtoGrupo(grupoDto, result);
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Grupo grp = this.serv.salvar(grupo);

		
		response.setData(this.converterGrupoDto(grp));	
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
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
	public ResponseEntity<Response<GrupoDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody GrupoDto grupoDto, BindingResult result){
		
		Response<GrupoDto> response = new Response<GrupoDto>();
		validarGrupo(grupoDto, result);
		
		grupoDto.setId(Optional.of(id));
		
		Grupo grupo = null;
		if (!result.hasErrors()) {
			grupo = this.converterDtoGrupo(grupoDto, result);
		}
		
		if (result.hasErrors()) {			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Grupo grp = this.serv.salvar(grupo);
		response.setData(this.converterGrupoDto(grp));
		return ResponseEntity.ok(response);
	}
	
	private GrupoDto converterGrupoDto(Grupo grupo) {
		GrupoDto grupoDto = new GrupoDto();
		grupoDto.setId(Optional.of(grupo.getId()));
		grupoDto.setNome(grupo.getNome().toString());
		return grupoDto;
	}
	
	private Grupo converterDtoGrupo(GrupoDto grupoDto, BindingResult result) {
		Grupo grupo = new Grupo();
		
		if (grupoDto.getId().isPresent()) {
			Optional<Grupo> lanc = this.serv.buscarPorId(grupoDto.getId().get());
			if (lanc.isPresent()) {
				grupo = lanc.get();
			} else {
				result.addError(new ObjectError("grupo", "Grupo não encontrado."));
			}
		}
		
		grupo.setNome(grupoDto.getNome());
		
		return grupo;
	}
	
	private void validarGrupo(GrupoDto grupoDto, BindingResult result) {
		
		if (grupoDto.getId() == null) {
			result.addError(new ObjectError("grupo", "Grupo não informado."));
			return;
		}
	
		Optional<Grupo> grupo = this.serv.buscarPorId(grupoDto.getId().get());
		if (!grupo.isPresent()) {
			result.addError(new ObjectError("grupo", "Grupo não cadastrado. Id inexistente"));
			return;
		}
		
		//this.funcionarioService.buscarPorCpf(cadastroPFDto.getCpf())
		//	.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));		
	}
	

}
