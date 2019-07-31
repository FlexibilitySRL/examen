package ar.com.flexibility.examen.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.entity.Producto;
import ar.com.flexibility.examen.domain.repositorys.ProductRepo;

public class ProductoService {
	@Autowired
	ProductRepo productorepo;
	
	public Iterable<Producto> vertodoProductos(){
		return productorepo.findAll();
	}
	
}
