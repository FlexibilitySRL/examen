package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CRUD service implementation for {@link Product}
 */
@Service
@Transactional
public class ProductService extends AbstractCrudService<Product, Long> {

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.repository = productRepository;
	}

}
