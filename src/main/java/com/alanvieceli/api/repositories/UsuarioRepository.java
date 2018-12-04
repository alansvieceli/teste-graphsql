package com.alanvieceli.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanvieceli.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
