package ar.com.flexibility.examen.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.entity.Vendedor;
import ar.com.flexibility.examen.domain.repositorys.VendedorRepo;

public class VendedorService {

	@Autowired
	VendedorRepo comprarepo;
	
	public Iterable<Vendedor> verCompras(){
		return comprarepo.findAll();
	
	}
	
}
