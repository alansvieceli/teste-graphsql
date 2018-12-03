package com.alanvieceli.api.graphql;

import java.util.List;

import com.alanvieceli.api.models.Empregado;
import com.alanvieceli.api.repositories.EmpregadoRepository;
import com.alanvieceli.api.repositories.ProjetoRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

	private EmpregadoRepository empregadoRepository;
	private ProjetoRepository projetoRepository;

	public Query(EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository) {
		this.empregadoRepository = empregadoRepository;
		this.projetoRepository = projetoRepository;
	}

	public List<Empregado> obterEmpregados() {
		return empregadoRepository.findAll();
	}

	public long contarEmpregados() {
		return empregadoRepository.count();
	}

	public Empregado obterEmpregadoPorId(Long id) {
		return empregadoRepository.findById(id).orElseThrow(null);
	}

}
