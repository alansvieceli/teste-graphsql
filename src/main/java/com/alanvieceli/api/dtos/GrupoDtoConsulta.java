package com.alanvieceli.api.dtos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GrupoDtoConsulta {

	private Optional<Long> id;
	private String nome;
	private Date data_criacao;
	private Date data_atualizacao;
	private List<UsuarioDtoGrupo> usuarios;
	
	public GrupoDtoConsulta() {

	}

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
	
	public List<UsuarioDtoGrupo> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDtoGrupo> usuarios) {
		this.usuarios = usuarios;
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

}
