package com.alanvieceli.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alanvieceli.api.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}

