package com.alanvieceli.api.dtos;

import java.util.Optional;

public class UsuarioDtoGrupo {

	private Optional<Long> id;
	private String nome;
	private String email;

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
