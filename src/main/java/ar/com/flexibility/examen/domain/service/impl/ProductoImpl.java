package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.support.Repositories;

import ar.com.flexibility.examen.domain.repository.IProductoRepo;
import ar.com.flexibility.examen.domain.model.Producto;


@Service("servicio")
public class ProductoImpl {
	
	@Autowired
	@Qualifier("repositorioProducto")
	private IProductoRepo productoRepo;
	private static final Log Logger = LogFactory.getLog(ProductoImpl.class);
	
	

	public boolean insertar (Producto producto) {
		
		Logger.info("insertando producto");
		
		try {
			productoRepo.save(producto);
			Logger.info("producto agregado");			 
			 return true;
		}catch(Exception e) {
			Logger.info("no pudo agregarse el producto"); 
			return false;
		}
		
	}
		

	public List<Producto> obtenerLista(){
		
		return productoRepo.findAll();
		
	}
	
	
	public Producto modificar(Producto producto) {
		
		return productoRepo.save(producto);
	}
	
	
	public boolean eliminar (int id) {
		try {
			Producto producto = productoRepo.findById(id);
			productoRepo.delete(producto);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}

