package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.repository.IProductoRepo;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoService;

@Service
public class ProductoImpl implements ProductoService{
	
	@Autowired
	private IProductoRepo productoRepo;
	
	@Override
	public Producto insertar(Producto producto) {
		return productoRepo.save(producto); 
	}
		
	@Override
	public List<Producto> findAll(){
		
		return productoRepo.findAll();
	}
	
	@Override
	public Producto modificar(Producto producto) {
		
		return productoRepo.save(producto);
	}
	
	@Override
	public Producto eliminar (Producto producto) {
		
		return productoRepo.save(producto);
	}
	
}

