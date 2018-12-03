package com.alanvieceli.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanvieceli.api.models.Empregado;
import com.alanvieceli.api.repositories.EmpregadoRepository;

@Service
public class EmpregadoService {
	
	@Autowired
	EmpregadoRepository rep;
	
	public Empregado salvar(Empregado empregado) {
		return rep.save(empregado);		
	}
	
	public List<Empregado> listarTodos(){
		return rep.findAll();
	}

}
