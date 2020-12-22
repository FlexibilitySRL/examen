package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Product;

public interface ProductService {
	public abstract Product getOne(long id); 
	public abstract List<Product> getAll();
	public abstract Product add(Product product);
	public abstract Product modify(Product product); 
	public abstract void delete (long id);
}
