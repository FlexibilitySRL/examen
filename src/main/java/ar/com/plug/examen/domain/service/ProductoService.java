package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.dto.ProductoAltaDto;
import ar.com.plug.examen.domain.dto.ProductoUpdateDto;
import ar.com.plug.examen.domain.model.Producto;

public interface ProductoService {

	public List<Producto> getProducts();
	
	public Optional<Producto> findById(Integer id);

	public Producto createProduct(ProductoAltaDto request);
	
	public Producto updateProduct(ProductoUpdateDto request);
	
	public void deleteProduct(Integer id);
	
}
