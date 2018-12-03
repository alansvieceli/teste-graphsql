package com.alanvieceli.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanvieceli.api.models.Empregado;
import com.alanvieceli.api.services.EmpregadoService;

@RestController
@RequestMapping("/api")
public class EmpregadoController {
	
	@Autowired
	private EmpregadoService serv;
	
	@GetMapping("/empregados")
	public ResponseEntity<?> listar(){
		List<Empregado> lista = serv.listarTodos();
    	return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping("/empregado")
	public ResponseEntity<?> create(@RequestBody Empregado empregado){
		Empregado obj = serv.salvar(empregado);
		return ResponseEntity.ok().body(obj);		
	}

}
