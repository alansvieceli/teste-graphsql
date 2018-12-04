package com.alanvieceli.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alanvieceli.api.graphql.Mutation;
import com.alanvieceli.api.graphql.Query;
import com.alanvieceli.api.services.GrupoService;

@SpringBootApplication
public class TesteGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteGraphqlApplication.class, args);
	}
	
	/*

	@Bean
	public Query query(GrupoService grupoService) {
		return new Query(grupoService);
	}
	
	@Bean
	public Mutation mutation(GrupoService grupoService) {
		return new Mutation(grupoService);
	}
	
	*/
}
