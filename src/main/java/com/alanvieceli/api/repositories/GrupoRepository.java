package com.alanvieceli.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alanvieceli.api.models.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	Page<Grupo> findAll(Pageable pageable);

}
