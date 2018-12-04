package com.alanvieceli.api.graphql;

import java.util.Optional;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.services.GrupoService;
import com.alanvieceli.api.services.UsuarioService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class Mutation implements GraphQLMutationResolver {

	private GrupoService grupoService;
	private UsuarioService usuarioService;

	public Mutation(GrupoService grupoService, UsuarioService usuarioService) {
		this.grupoService = grupoService;
		this.usuarioService = usuarioService;
	}
	
	public Grupo novoGrupo(String nome) {
		Grupo grupo = new Grupo(nome);
		this.grupoService.salvar(grupo);
		return grupo;
	}
	
	public boolean deletarGrupo(Long id) {
		
		Optional<Grupo> grupo = this.grupoService.buscarPorId(id);
		if (grupo.isPresent()) {
			this.grupoService.remover(id);
			return true;
		} else {
			return false;
		}
	}

}
