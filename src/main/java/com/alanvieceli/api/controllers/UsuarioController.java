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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alanvieceli.api.dtos.UsuarioDtoConsulta;
import com.alanvieceli.api.dtos.UsuarioDtoPersistir;
import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.responses.Response;
import com.alanvieceli.api.services.GrupoService;
import com.alanvieceli.api.services.UsuarioService;
import com.alanvieceli.api.utils.Funcoes;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService servUsuario;
	
	@Autowired
	private GrupoService servGrupo;

	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	@GetMapping
	public ResponseEntity<Response<Page<UsuarioDtoConsulta>>> listar(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir) {

		Response<Page<UsuarioDtoConsulta>> response = new Response<Page<UsuarioDtoConsulta>>();

		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);

		Page<Usuario> usuarios = this.servUsuario.listarTodos(pageRequest);
		if (usuarios != null) {
			Page<UsuarioDtoConsulta> usuariosDto = usuarios
					.map(usuario -> Funcoes.converterUsuarioDtoConsulta(usuario));
			response.setData(usuariosDto);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDtoConsulta>> buscarPorId(@PathVariable("id") Long id) {
		Response<UsuarioDtoConsulta> response = new Response<UsuarioDtoConsulta>();
		Optional<Usuario> grupo = this.servUsuario.buscarPorId(id);

		if (!grupo.isPresent()) {
			response.getErrors().add("Usuário não encontrado para o Id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(Funcoes.converterUsuarioDtoConsulta(grupo.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<UsuarioDtoConsulta>> cadastrar(@Valid @RequestBody UsuarioDtoPersistir usuarioDto,
			BindingResult result) {
		Response<UsuarioDtoConsulta> response = new Response<UsuarioDtoConsulta>();

		Usuario usuario = Funcoes.converterDtoPersistirUsuario(servGrupo, usuarioDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Usuario usu = this.servUsuario.salvar(usuario);

		response.setData(Funcoes.converterUsuarioDtoConsulta(usu));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Usuario> usuario = this.servUsuario.buscarPorId(id);

		if (!usuario.isPresent()) {
			response.getErrors().add("Erro ao remover usuario. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.servUsuario.remover(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
}
