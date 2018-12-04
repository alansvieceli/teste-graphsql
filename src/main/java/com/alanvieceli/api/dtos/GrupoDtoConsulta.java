package com.alanvieceli.api.dtos;

import java.util.List;

public class GrupoDtoConsulta extends GrupoDtoUsuario {

	private List<UsuarioDtoGrupo> usuarios;

	public GrupoDtoConsulta() {
		super();
	}

	public List<UsuarioDtoGrupo> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDtoGrupo> usuarios) {
		this.usuarios = usuarios;
	}
}
