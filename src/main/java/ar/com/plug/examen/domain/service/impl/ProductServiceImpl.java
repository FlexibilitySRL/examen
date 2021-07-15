package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.repositories.ProductRepository;
import ar.com.plug.examen.domain.service.IProductRepo;
import ar.com.plug.examen.domain.validators.Validator;

@Service
public class ProductServiceImpl implements IProductRepo {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Validator validator;

	@Override
	public ProductDTO findClientById(long id) throws ResourceNotFoundError {
		return this.productMapper.productToProductDTO(this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Product not found")));
	}

	@Override
	public List<ProductDTO> findAll() {
		return this.productMapper.productToListProductDTO(this.productRepository.findAll());
	}

	@Override
	public ProductDTO save(ProductDTO productoDTO) throws BadRequestError {
		this.validator.validateProduct(productoDTO);
		return this.productMapper.productToProductDTO(this.productRepository.save(this.productMapper.productDTOtoProduct(productoDTO)));
	}

	@Override
	public ProductDTO update(ProductDTO productoDTO) throws ResourceNotFoundError, BadRequestError {
		this.validator.validateProduct(productoDTO);
		this.productRepository.findById(productoDTO.getId()).orElseThrow(() -> new ResourceNotFoundError("Product not found"));
		return this.productMapper.productToProductDTO(this.productRepository.save(this.productMapper.productDTOtoProduct(productoDTO)));
	}

	@Override
	public void delete(long id) throws ResourceNotFoundError {
		this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Product not found"));
		this.productRepository.deleteById(id);
	}

}
