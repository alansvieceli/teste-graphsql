package com.alanvieceli.api.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.alanvieceli.api.dtos.GrupoDtoConsulta;
import com.alanvieceli.api.dtos.GrupoDtoPersistir;
import com.alanvieceli.api.dtos.GrupoDtoUsuario;
import com.alanvieceli.api.dtos.UsuarioDtoConsulta;
import com.alanvieceli.api.dtos.UsuarioDtoGrupo;
import com.alanvieceli.api.dtos.UsuarioDtoPersistir;
import com.alanvieceli.api.models.Grupo;
import com.alanvieceli.api.models.Usuario;
import com.alanvieceli.api.services.GrupoService;

public class Funcoes {

	public static GrupoDtoConsulta converterGrupoDtoConsulta(Grupo grupo) {
		GrupoDtoConsulta grupoDtoConsulta = new GrupoDtoConsulta();
		grupoDtoConsulta.setId(grupo.getId());
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

	public static Grupo converterDtoPersistirGrupo(GrupoDtoPersistir grupoDto, BindingResult result) {
		Grupo grupo = new Grupo();

		grupo.setNome(grupoDto.getNome());

		return grupo;
	}

	public static GrupoDtoUsuario converteGrupoDtoUsuario(Grupo grupo) {
		GrupoDtoUsuario grupoDtoConsulta = new GrupoDtoUsuario();
		grupoDtoConsulta.setId(grupo.getId());
		grupoDtoConsulta.setNome(grupo.getNome().toString());
		grupoDtoConsulta.setData_criacao(grupo.getData_criacao());
		grupoDtoConsulta.setData_atualizacao(grupo.getData_atualizacao());
		return grupoDtoConsulta;
	}

	public static Usuario converterDtoPersistirUsuario(GrupoService serv, UsuarioDtoPersistir usuarioDto,
			BindingResult result) {
		Usuario usuario = new Usuario();

		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());

		Long id_grupo = usuarioDto.getGrupo_id();
		if (id_grupo > 0) {
			Optional<Grupo> grupo = serv.buscarPorId(usuarioDto.getGrupo_id());
			if (grupo.isPresent()) {
				usuario.setGrupo(grupo.get());
			} else {
				result.addError(new ObjectError("grupo", "Grupo não cadastrado. Id inexistente"));
			}
		}

		return usuario;

	}

	public static UsuarioDtoGrupo converterUsuarioDtoGrupo(Usuario usuario) {
		UsuarioDtoGrupo usuarioDtoGrupo = new UsuarioDtoGrupo();
		usuarioDtoGrupo.setId(usuario.getId());
		usuarioDtoGrupo.setNome(usuario.getNome().toString());
		usuarioDtoGrupo.setEmail(usuario.getEmail());
		return usuarioDtoGrupo;
	}

	public static UsuarioDtoConsulta converterUsuarioDtoConsulta(Usuario usuario) {
		UsuarioDtoConsulta usuarioDtoConsulta = new UsuarioDtoConsulta();
		usuarioDtoConsulta.setId(usuario.getId());
		usuarioDtoConsulta.setNome(usuario.getNome().toString());
		usuarioDtoConsulta.setEmail(usuario.getEmail());
		usuarioDtoConsulta.setData_atualizacao(usuario.getData_atualizacao());
		usuarioDtoConsulta.setData_criacao(usuario.getData_criacao());

		if (usuario.getGrupo() != null) {
			usuarioDtoConsulta.setGrupo(converteGrupoDtoUsuario(usuario.getGrupo()));
		}
		return usuarioDtoConsulta;
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
