package ar.com.flexibility.examen.domain.service;


import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.entity.Cliente;
import ar.com.flexibility.examen.domain.repositorys.ClienteRepo;

public class ClienteService {

	@Autowired
	ClienteRepo clienterepo;
	
	
	
	public Iterable<Cliente> verClientes(){
		return clienterepo.findAll();
		
	}
	public Cliente BuscaNombre(String nombre) throws Exception{
		if(nombre.isEmpty()) {
			throw new Exception("el campo esta vacio");
		}
		return clienterepo.findByNombre(nombre);
	}
}
