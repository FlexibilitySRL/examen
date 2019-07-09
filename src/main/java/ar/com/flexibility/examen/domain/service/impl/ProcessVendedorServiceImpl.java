package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.IVendedorDao;
import ar.com.flexibility.examen.domain.model.entity.Vendedor;
import ar.com.flexibility.examen.domain.service.ProcessVendedorService;

@Service
public class ProcessVendedorServiceImpl implements ProcessVendedorService {

	@Autowired
	private IVendedorDao vendedorDao;
	
	@Override
	public List<Vendedor> findAll() {
		return (List<Vendedor>) vendedorDao.findAll();
	}
	
	@Override
	public void save(Vendedor vendedor) {
		vendedorDao.save(vendedor);
	}
	
	@Override
	public Vendedor findOne(Long id) {
		return vendedorDao.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		vendedorDao.delete(id);
	}
}