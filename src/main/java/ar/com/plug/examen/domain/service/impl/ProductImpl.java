package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;

@Service
public class ProductImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> consultar() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void crear(Product customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Product customer) {
		// TODO Auto-generated method stub
		
	}

}
