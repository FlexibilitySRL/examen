package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.ProductService#create(ar.com.flexibility.examen.domain.model.Product)
	 */
	@Override
	public Product create(Product product) {
		
		return productRepository.save(product);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.ProductService#update(ar.com.flexibility.examen.domain.model.Product)
	 */
	@Override
	public Product update(Product product) {
		
		if (productRepository.findOne(product.getId()) != null) {
			return productRepository.saveAndFlush(product);
		} else {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.ProductService#findById(java.lang.Long)
	 */
	@Override
	public Product findById(Long idProduct) {
		
		return productRepository.findOne(idProduct);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.ProductService#findAll()
	 */
	@Override
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.ProductService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long idProduct) {
		
		productRepository.delete(idProduct);
	}

}
