package com.alanvieceli.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Override
	public Page<Usuario> listarTodos(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> buscarPorId(Long optional) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grupo salvar(Usuario grupo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

}
