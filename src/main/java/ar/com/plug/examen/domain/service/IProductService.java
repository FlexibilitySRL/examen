package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Product;

public interface IProductService {

	public List<Product> findAll();
	
	public Product save(Product cliente);
	
	public Product findById(Long id);
	
	public void delete(Long id);
	
}
