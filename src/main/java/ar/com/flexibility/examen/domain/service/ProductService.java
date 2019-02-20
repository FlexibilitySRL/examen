/**
 * 
 */
package ar.com.flexibility.examen.domain.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;

/**
 * @author rosalizaracho
 *
 */
@Service
@Transactional(readOnly=true)
public class ProductService {
  
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Product createProduct(Product product) {
		return productRepository.save(product);
		
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}
}
