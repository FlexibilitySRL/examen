package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.service.ProductoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	/*
	 * TODO usar 
	 * private ProductoRepository productoRepository;
	 */
	
	private Map<Long, Producto> productos = new HashMap<Long,Producto>();
	private static final AtomicLong id = new AtomicLong();
	
	public ProductoServiceImpl() {
		Producto p1 = new Producto(id.getAndIncrement(), "producto A");
		Producto p2 = new Producto(id.getAndIncrement(), "producto B");
		Producto p3 = new Producto(id.getAndIncrement(), "producto C");
		
		productos.put(p1.getId(), p1);
		productos.put(p2.getId(), p2);
		productos.put(p3.getId(), p3);
		
	}
	
	public void setProductos(List<Producto> productos){
		for (Producto p: productos) {
			this.productos.put(p.getId(), p);
		}
		
	}
	
	
	@Override
	public Producto findById(long id) {
		if (productos.containsKey(id)) {
			return productos.get(id);
		}
		
		return null;
	}

	@Override
	public void update(Producto producto) {
		productos.put(producto.getId(), producto);
	}

	@Override
	public void deleteById(long id) {
		productos.remove(id);
	}

	@Override
	public Producto save(Producto producto) {
		producto.setId(id.getAndIncrement());
		productos.put(producto.getId(), producto);
		return producto;
	}

	@Override
	public List<Producto> getAll() {
		return new ArrayList<Producto>(productos.values());
	}
	
	
}
