package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.repository.ProductoRepository;

@Service
public class ProcessProductoServiceImpl {
	@Autowired
	ProductoRepository repo;

	public Producto addProducto(Producto producto) {
		producto.setIdProducto(0);
		return repo.save(producto);
	}

	public Optional<Producto> getProducto(Integer producto) {
		return repo.findById(producto);
	}

	public void delete(Integer producto) {
		repo.deleteById(producto);
	}
}
