package ar.com.flexibility.examen.domain.service;

import java.util.List;
import ar.com.flexibility.examen.domain.model.Producto;

public interface ProductoService {
		
	
	public Producto insertar( Producto producto);
	public Producto modificar (Producto producto);
	public List<Producto> findAll();
	public Producto eliminar( Producto producto);
	
}
