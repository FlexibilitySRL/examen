package ar.com.flexibility.examen.domain.service;

import java.util.List;
import ar.com.flexibility.examen.domain.model.Cliente;


public interface ClienteService {
		
	
	public Cliente insertar( Cliente cliente);
	public Cliente modificar (Cliente cliente);
	public List<Cliente> findAll();
	public Cliente eliminar( Cliente cliente);
	
}
