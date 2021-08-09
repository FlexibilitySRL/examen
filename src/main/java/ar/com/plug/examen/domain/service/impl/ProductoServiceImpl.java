package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.ProductoDao;
import ar.com.plug.examen.domain.dto.ProductoAltaDto;
import ar.com.plug.examen.domain.dto.ProductoUpdateDto;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDao dao;
	
	@Override
	public List<Producto> getProducts() {
		return dao.findAll();
	}

	@Override
	public Optional<Producto> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Producto createProduct(ProductoAltaDto source) {
		Producto entity = new Producto();
		entity.setNombre(source.getNombre());
		return dao.save(entity);
	}

	@Override
	public Producto updateProduct(ProductoUpdateDto source) {
		Producto entity = dao.findById(source.getId()).get();
		if(Optional.ofNullable(source.getNombre()).isPresent())
			entity.setNombre(source.getNombre());
		return dao.save(entity);
	}

	@Override
	public void deleteProduct(Integer id) {
		dao.deleteById(id);
	}

}
