package com.alanvieceli.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alanvieceli.api.graphql.Mutation;
import com.alanvieceli.api.graphql.Query;
import com.alanvieceli.api.services.GrupoService;
import com.alanvieceli.api.services.UsuarioService;

@SpringBootApplication
public class TesteGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteGraphqlApplication.class, args);
	}
	
	@Bean
	public Query query(GrupoService grupoService, UsuarioService usuarioService) {
		return new Query(grupoService, usuarioService);
	}
	
	@Bean
	public Mutation mutation(GrupoService grupoService,  UsuarioService usuarioService) {
		return new Mutation(grupoService, usuarioService);
	}	
	
}
