package com.alanvieceli.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.models.Usuario;

public interface UsuarioService {
	
	Page<Usuario> listarTodos(PageRequest pageRequest);
	
	List<Usuario> listarTodos();

	Optional<Usuario> buscarPorId(Long optional);
	
	Usuario salvar(Usuario grupo);
	
	void remover(Long id);

}
