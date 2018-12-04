package com.alanvieceli.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.repositories.UsuarioRepository;
import com.alanvieceli.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository rep;

	@Override
	public Page<Usuario> listarTodos(PageRequest pageRequest) {
		return this.rep.findAll(pageRequest);
	}

	@Override
	public List<Usuario> listarTodos() {
		return this.rep.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return this.rep.findById(id);
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return this.rep.save(usuario);
	}

	@Override
	public void remover(Long id) {
		this.rep.deleteById(id);
	}

}
