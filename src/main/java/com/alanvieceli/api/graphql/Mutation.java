package com.alanvieceli.api.graphql;

import java.util.Optional;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.services.GrupoService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class Mutation implements GraphQLMutationResolver {

	private GrupoService grupoService;

	public Mutation(GrupoService grupoService) {
		this.grupoService = grupoService;
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
