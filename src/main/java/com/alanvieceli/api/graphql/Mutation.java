package com.alanvieceli.api.graphql;

import java.util.Date;

import com.alanvieceli.api.models.Empregado;
import com.alanvieceli.api.repositories.EmpregadoRepository;
import com.alanvieceli.api.repositories.ProjetoRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class Mutation implements GraphQLMutationResolver {

	private EmpregadoRepository empregadoRepository;
	private ProjetoRepository projetoRepository;

	public Mutation(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository) {
		this.empregadoRepository = empregadoRepository;
		this.projetoRepository = projetoRepository;
	}

	public Empregado novoEmpregado(String nome, Integer idade) {
		Empregado empregado = new Empregado(nome, idade, new Date());
		empregadoRepository.save(empregado);
		return empregado;
	}

	public boolean deletarEmpregado(Long id) {
		empregadoRepository.deleteById(id);
		return true;
	}

}