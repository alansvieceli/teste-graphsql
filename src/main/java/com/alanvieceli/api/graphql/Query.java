package com.alanvieceli.api.graphql;

import java.util.List;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.services.GrupoService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {
	
	private GrupoService grupoService;
	
	public Query(GrupoService grupoService) {
	      this.grupoService = grupoService;	      
	   }
	
	public List<Grupo> obteGrupos() {
		return grupoService.listarTodos();
	}	

}
