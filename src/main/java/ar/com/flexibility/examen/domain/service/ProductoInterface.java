package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Producto;

public interface ProductoInterface {

	List<Producto> obtenerProductos();
	
	boolean modificar(Producto producto);
	
	boolean eliminar(Long idProducto);
	
	boolean crear(Producto producto);
	
	Producto obtenerProducto(Long idProducto);
	
}
