package com.alanvieceli.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.alanvieceli.api.models.Grupo;

public interface GrupoService {

	Page<Grupo> listarTodos(PageRequest pageRequest);
	
	List<Grupo> listarTodos();

	Optional<Grupo> buscarPorId(Long optional);
	
	Grupo salvar(Grupo grupo);
	
	void remover(Long id);
}
