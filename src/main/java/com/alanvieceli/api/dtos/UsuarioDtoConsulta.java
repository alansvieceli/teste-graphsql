package com.alanvieceli.api.dtos;

import java.util.Date;

public class UsuarioDtoConsulta {
	
	private Long id;	
	private String nome;
	private String email;
	private Date data_criacao;
	private Date data_atualizacao;
	private GrupoDtoUsuario grupo;
	
	public UsuarioDtoConsulta() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Date getData_atualizacao() {
		return data_atualizacao;
	}

	
	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}
	
	public GrupoDtoUsuario getGrupo() {
		return grupo;
	}
	

	public void setGrupo(GrupoDtoUsuario grupo) {
		this.grupo = grupo;
	}
	

}
