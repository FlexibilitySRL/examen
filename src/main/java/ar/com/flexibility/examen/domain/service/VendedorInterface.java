package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Vendedor;

public interface VendedorInterface {
	
	List<Vendedor> obtenerVendedores();
	
	boolean modificar(Vendedor vendedor);
	
	boolean eliminar(Long idVendedor);
	
	boolean crear(Vendedor vendedor);
	
	Vendedor obtener(Long idVendedor);
}
