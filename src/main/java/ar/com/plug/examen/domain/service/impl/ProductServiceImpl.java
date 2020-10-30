/**
 * 
 */
package ar.com.plug.examen.domain.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;

/**
 * @author hellraiser
 *
 */
@Service
public class ProductServiceImpl {

	@Autowired
	ProductRepository productRepo;

	
	public Product createProduct(@Valid Product entity) {
		
		return productRepo.save(entity);
		
	}
	
}
