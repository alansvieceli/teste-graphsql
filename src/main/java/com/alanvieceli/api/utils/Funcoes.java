package com.alanvieceli.api.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.alanvieceli.api.dtos.GrupoDtoConsulta;
import com.alanvieceli.api.dtos.GrupoDtoPersistir;
import com.alanvieceli.api.dtos.UsuarioDtoGrupo;
import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.services.GrupoService;

public class Funcoes {

	public static GrupoDtoConsulta converterGrupoDto(Grupo grupo) {
		GrupoDtoConsulta grupoDtoConsulta = new GrupoDtoConsulta();
		grupoDtoConsulta.setId(Optional.of(grupo.getId()));
		grupoDtoConsulta.setNome(grupo.getNome().toString());
		grupoDtoConsulta.setData_criacao(grupo.getData_criacao());
		grupoDtoConsulta.setData_atualizacao(grupo.getData_atualizacao());

		List<Usuario> usuarios = grupo.getUsuarios();
		if (usuarios != null) {
			List<UsuarioDtoGrupo> usuariosDto = usuarios.stream()
					.map(usuario -> Funcoes.converterUsuarioDtoGrupo(usuario)).collect(Collectors.toList());
			grupoDtoConsulta.setUsuarios(usuariosDto);
		}

		return grupoDtoConsulta;
	}

	public static UsuarioDtoGrupo converterUsuarioDtoGrupo(Usuario usuario) {
		UsuarioDtoGrupo usuarioDtoGrupo = new UsuarioDtoGrupo();
		usuarioDtoGrupo.setId(Optional.of(usuario.getId()));
		usuarioDtoGrupo.setNome(usuario.getNome().toString());
		usuarioDtoGrupo.setEmail(usuario.getEmail());
		return usuarioDtoGrupo;
	}

	public static Grupo converterDtoPersistirGrupo(GrupoService serv, GrupoDtoPersistir grupoDto,
			BindingResult result) {
		Grupo grupo = new Grupo();

		grupo.setNome(grupoDto.getNome());

		return grupo;
	}

	public static void validarGrupoDtoPersistir(GrupoService serv, Long id, GrupoDtoPersistir grupoDtoConsulta,
			BindingResult result) {

		Optional<Grupo> grupo = serv.buscarPorId(id);
		if (!grupo.isPresent()) {
			result.addError(new ObjectError("grupo", "Grupo não cadastrado. Id inexistente"));
			return;
		}

	}

}
