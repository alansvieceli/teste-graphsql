package com.alanvieceli.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.repositories.GrupoRepository;
import com.alanvieceli.api.services.GrupoService;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	GrupoRepository rep;

	public Page<Grupo> listarTodos(PageRequest pageRequest) {
		return this.rep.findAll(pageRequest);
	}

	@Override
	public Optional<Grupo> buscarPorId(Long id) {
		return this.rep.findById(id);
	}

	@Override
	public Grupo salvar(Grupo grupo) {
		return this.rep.save(grupo);	
	}

	@Override
	public void remover(Long id) {
		this.rep.deleteById(id);		
	}

}
