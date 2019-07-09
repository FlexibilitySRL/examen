package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.entity.Producto;

public interface ProcessProductoService {

	public List<Producto> findAll();
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);
}
