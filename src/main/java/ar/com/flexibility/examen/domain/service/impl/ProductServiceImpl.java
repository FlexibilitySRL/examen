package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

    @Override
    @Transactional(readOnly = true)
	public Product getProductById(Long productId) {
    	
    	Product product = productRepository.findOne(productId);
        
    	if (product == null ){
    		
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
		
		productRepository.delete(productId);

	}
	
}
