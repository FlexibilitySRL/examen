package ar.com.flexibility.examen.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.entity.Compra;
import ar.com.flexibility.examen.domain.repositorys.CompraRepo;

public class CompraService {
	@Autowired
	CompraRepo comprarepo;
	
	public Iterable<Compra> verCompras(){
		return comprarepo.findAll();
	
	}
	
	
}
