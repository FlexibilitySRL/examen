package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public ProductDTO findProductById(long id) throws ResourceNotFoundError {
		logger.info("Finding product with ID: " + id);
		ProductDTO productDTO = this.productMapper.productToProductDTO(this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Product not found")));
		logger.info("Finding product DONE");
		return productDTO;
	}

	@Override
	public List<ProductDTO> findAll() {
		logger.info("Finding all products");
		List<ProductDTO> products = this.productMapper.productToListProductDTO(this.productRepository.findAll()); 
		logger.info("Finding all products DONE");
		return products;
	}

	@Transactional
	@Override
	public ProductDTO save(ProductDTO productoDTO) throws BadRequestError {
		logger.info("Validating product");
		this.validator.validateProduct(productoDTO);
		logger.info("Validating product OK");
		logger.info("Saving product...");
		ProductDTO p = this.productMapper.productToProductDTO(this.productRepository.save(this.productMapper.productDTOtoProduct(productoDTO)));
		logger.info("Saving producto DONE");
		return p;
	}

	@Transactional
	@Override
	public ProductDTO update(ProductDTO productoDTO) throws ResourceNotFoundError, BadRequestError {
		logger.info("Validating product");
		this.validator.validateProduct(productoDTO);
		logger.info("Validating product DONE");
		logger.info("Finding product");
		this.productRepository.findById(productoDTO.getId()).orElseThrow(() -> new ResourceNotFoundError("Product not found"));
		logger.info("Finding product DONE");
		ProductDTO p = this.productMapper.productToProductDTO(this.productRepository.save(this.productMapper.productDTOtoProduct(productoDTO)));
		logger.info("Product updated");
		return p;
	}

	@Transactional
	@Override
	public void delete(long id) throws ResourceNotFoundError {
		logger.info("Finding product by ID: " + id);
		this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Product not found"));
		logger.info("Product found");
		logger.info("Deleting product");
		this.productRepository.deleteById(id);
		logger.info("Deleting product DONE");
	}

}
