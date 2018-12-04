package com.alanvieceli.api.graphql;

import java.util.List;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.services.GrupoService;
import com.alanvieceli.api.services.UsuarioService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

	private GrupoService grupoService;
	private UsuarioService usuarioService;

	public Query(GrupoService grupoService, UsuarioService usuarioService) {
		this.grupoService = grupoService;
		this.usuarioService = usuarioService;
	}

	public List<Grupo> obteGrupos() {
		return grupoService.listarTodos();
	}
	
	public List<Usuario> obterUsuarios() {
		return usuarioService.listarTodos();
	}

	public Grupo obterGrupoPorId(Long id) {
		return grupoService.buscarPorId(id).orElseThrow(null);
	}

}
