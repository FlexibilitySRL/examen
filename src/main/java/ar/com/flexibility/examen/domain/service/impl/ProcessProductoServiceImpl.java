package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.IProductoDao;
import ar.com.flexibility.examen.domain.model.entity.Producto;
import ar.com.flexibility.examen.domain.service.ProcessProductoService;

@Service
public class ProcessProductoServiceImpl implements ProcessProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	public void save(Producto producto) {
		productoDao.save(producto);
	}
	
	@Override
	public Producto findOne(Long id) {
		return productoDao.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		productoDao.delete(id);
	}
}
