package com.alanvieceli.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UsuarioDtoPersistir {

	private String nome;
	private String email;
	private Long grupo_id;
	
	public UsuarioDtoPersistir() {
		
	}
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "E-mail não pode ser vazio.")
	@Length(min = 3, max = 200, message = "E-mail deve conter entre 3 e 200 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getGrupo_id() {
		return grupo_id;
	}

	public void setGrupo_id(Long grupo_id) {
		this.grupo_id = grupo_id;
	}

}
