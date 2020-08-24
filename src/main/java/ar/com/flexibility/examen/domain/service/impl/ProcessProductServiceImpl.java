package ar.com.flexibility.examen.domain.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProcessProductService;
import ar.com.flexibility.examen.repository.ProductRepository;

@Service
public class ProcessProductServiceImpl implements ProcessProductService {

	private final Logger log = LoggerFactory.getLogger(ProcessProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ProductApi create(ProductApi productApi) {
		Product product = modelMapper.map(productApi, Product.class);
		product = productRepository.saveAndFlush(product);
		productApi = modelMapper.map(product, ProductApi.class);
		log.info("Product successfully created.");
		return productApi;
	}

	@Override
	public ProductApi update(Long productId, ProductApi productApi) {
		Product product = modelMapper.map(productApi, Product.class);
		product = productRepository.findById(productId);
		product.setName(productApi.getName());
		product.setType(productApi.getType());
		product = productRepository.saveAndFlush(product);
		productApi = modelMapper.map(product, ProductApi.class);
		log.info("Product successfully updated.");
		return productApi;
	}

	@Override
	public String delete(ProductApi productApi) {
		Product product = modelMapper.map(productApi, Product.class);
		productRepository.delete(product);
		log.info("Product successfully deleted.");
		return "Product successfully deleted.";
	}
	
	@Override
	public ProductApi searchByName(String name) {
		Product product = productRepository.findByName(name);		
		ProductApi productApi = null;
		if(product != null) {
			productApi = modelMapper.map(product, ProductApi.class);
		}
		return productApi;
	}
}
