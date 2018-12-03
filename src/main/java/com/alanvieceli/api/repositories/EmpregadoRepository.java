package com.alanvieceli.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanvieceli.api.models.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

}
