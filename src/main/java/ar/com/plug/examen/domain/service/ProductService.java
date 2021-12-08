package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Product;

public interface ProductService {

	public List<Product> consultar();
	public void crear(Product customer);
	public void editar(Product customer);
}
