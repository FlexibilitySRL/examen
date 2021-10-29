package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.dto.ProductDto;

public interface IProductService {
	
	public List<ProductDto> findAll();
	
	public ProductDto findById(Long id) throws Exception;
	
	public ProductDto save(ProductDto product);
	
	public ProductDto update(ProductDto product) throws Exception;
	
	public void delete(Long id);
}
