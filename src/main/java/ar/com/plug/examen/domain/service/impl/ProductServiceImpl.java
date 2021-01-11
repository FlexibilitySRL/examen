package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repositories.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.validations.PairResult;
import ar.com.plug.examen.domain.validations.Validation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private Validation validation;
	
	@Override
	public ProductApi createProduct(ProductApi productApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateProduct(productApi);
		
		if(!result.isValid()) {
			log.error("Mandatory data is missing: " + result.getLeyend());
			throw new BadRequestException("Mandatory data is missing: " + result.getLeyend());
		}
		
		Product product = productRepository.save(productMapper.fillEntity(new Product(), productApi));

		log.info("The product " + product.getId() +" was succesfully created.");
		
		return productMapper.getDto(product);
	}

	@Override
	public ProductApi getProductById(Long id) {
		
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product with the id:" + id + " was not found."));
		
		return productMapper.getDto(product);
	}

	@Override
	public List<ProductApi> listAllProducts() {
		
		List<Product> products = productRepository.findAll();
		
		if(products.isEmpty()) {
			log.error("The product list is empty.");
		}
		
		return productMapper.getDto(products);
	}

	@Override
	public void removeProduct(Long id) {
		
		if(!productRepository.existsById(id)) {
			log.error("The product with the id:" + id + " does not exist.");
			throw new NotFoundException("The product with the id:\" + id + \" does not exist.");
		}
		
		productRepository.deleteById(id);
		
		log.info("The product with the id:" + id + " was succesfully deleted.");
		
	}

	@Override
	public ProductApi updateProduct(Long id, ProductApi productApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateProduct(productApi);
		
		if(!productRepository.existsById(id)) {
			log.error("The product with the id:" + id + " does not exist.");
			throw new NotFoundException("product with id " + id + " does not exist");
		}else if(!result.isValid()) {
			log.error("Mandatory data is missing: " + result.getLeyend());
			throw new BadRequestException("Mandatory data is missing: name");
		}
		
		validation.validateId(id, productApi.getId());
		
		Product prod = new Product();
				
		prod = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product with the id:" + id + " was not found."));
		
		prod.setName(productApi.getName());
		prod.setPrice(productApi.getPrice());
		prod.setDescription(productApi.getDescription());
		
		log.info("The product " + prod.getId() +" was succesfully updated.");
		
		return productMapper.getDto(productRepository.save(prod));
	}
	
}