package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;

/**
 * Implementation of the ProductService that uses a CrudRepository
 *
 * @author julimanfre@hotmail.com
 */

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    ProductRepository productRepository;
    
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public Product create(Product product) {
		
		LOGGER.info("Creating Product....");
 
 		Product productCreated = productRepository.save(product);
		
		if( productCreated==null){

			LOGGER.error("Product not Created....");

		}else{
			
			LOGGER.info("Product Created Succesfully....");
		}
		
		return productCreated;
	}

	@Override
	public Product update(Long id, Product product) {
		
 		Product productUpdated = productRepository.save(product);
		
		if( productUpdated==null){

			LOGGER.error("Product not Created....");

		}else{
			
			LOGGER.info("Product Created Succesfully....");
		}
		return productUpdated;

	}

	@Override
	public void delete(Long id) {
		
		LOGGER.info("Deleting Product by Id...." + id);

		productRepository.deleteById(id);;
	}

	@Override
	public List<Product> getProducts() {

		LOGGER.info("Retrieve All products....");

 		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {

		LOGGER.info("Retrieve product Id ...." + id);
		 
		
		Optional<Product> result = productRepository.findById(id);
		
		if (!result.isPresent()){
			
			LOGGER.info(" Product found Id ...." + id);

		}else{
			
			LOGGER.error(" Product not found Id ...." + id);
		}
		
		return result;	
		 
	}

	
    
}
