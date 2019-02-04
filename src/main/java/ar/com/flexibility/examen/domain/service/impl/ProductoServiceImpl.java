package ar.com.flexibility.examen.domain.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoService;
import ar.com.flexibility.examen.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public void removeByIdProducto(Integer id) {
		productoRepository.delete(id);
	}

	@Override
	public Producto findByIdProducto(Integer id) {
		return productoRepository.findOne(id);
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

}
