package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	/**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
	public Product getProductById(Long productId) {
    	
    	getLog().info("Find product: {}", productId);
        
    	Product product = productRepository.findOne(productId);
        
    	if (product == null ){
    		
    		getLog().info("Product with id: " + productId + " does not exists.");
    		
    		throw new RuntimeException("Product with id: " + productId + " does not exists.");
    		
    	}
    	return product;
	
    }

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		
		getLog().info("The product with id: " + productId + " will be deleted.");
		
		productRepository.delete(productId);

	}
	
	public Logger getLog() {
		return log;
	}

}
