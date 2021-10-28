package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.dao.jpa.ProductJpaDao;
import ar.com.plug.examen.domain.service.IProductService;
import ar.com.plug.examen.dto.ProductDto;
import ar.com.plug.examen.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductJpaDao dao;
	
	@Autowired
	private ProductMapper mapper;

	@Override
	public List<ProductDto> findAll() {
		return mapper.entityListToDtoList(dao.findAll());
	}

	@Override
	public ProductDto findById(Long id) throws Exception {
		return mapper.entityToDto(dao.findById(id).orElseThrow(() -> new Exception("No se encontro el producto Nro: " + id)));
	}

	@Override
	@Transactional
	public ProductDto save(ProductDto product) {
		return mapper.entityToDto(dao.save(mapper.dtoToEntity(product)));
	}
	
	@Override
	public ProductDto update (ProductDto product) throws Exception {
		if(dao.findById(product.getId()).isPresent()) {
			return mapper.entityToDto(dao.save(mapper.dtoToEntity(product)));
		}
		else {
			throw new Exception("No se puede actualizar el producto Nro:" + product.getId());
		}
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
		
}
