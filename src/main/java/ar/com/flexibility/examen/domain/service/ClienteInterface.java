package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.*;

public interface ClienteInterface {

	List<Cliente> obtenerClientes();
	
	boolean modificar(Cliente cliente);
	
	boolean eliminar(Long idCliente);
	
	boolean crear(Cliente cliente);
	
	Cliente obtener(Long idCliente);
}
