package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Producto;

public interface ProductoService {

	Producto findById(long id);
	void update(Producto producto);
	void deleteById(long id);
	Producto save(Producto producto);
	List<Producto> getAll();
}
