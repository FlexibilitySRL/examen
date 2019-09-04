package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {

	public List<ProductDTO> findAll();

	public ProductDTO findById(Long id);

	public ProductDTO save(ProductDTO dto);

	public Boolean delete(Long id);

	public ProductDTO entityToDto(Product entity);

	public Product dtoToEntity(ProductDTO dto);
}
