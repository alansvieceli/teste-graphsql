package com.alanvieceli.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alanvieceli.api.graphql.Mutation;
import com.alanvieceli.api.graphql.Query;
import com.alanvieceli.api.repositories.EmpregadoRepository;
import com.alanvieceli.api.repositories.ProjetoRepository;

@SpringBootApplication
public class TesteGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteGraphqlApplication.class, args);
	}

	@Bean
	public Query query(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository) {
		return new Query(empregadoRepository, projetoRepository);
	}

	@Bean
	public Mutation mutation(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository) {
		return new Mutation(empregadoRepository, projetoRepository);
	}

}
