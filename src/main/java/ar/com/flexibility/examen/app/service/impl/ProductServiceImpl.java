package ar.com.flexibility.examen.app.service.impl;

import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;


	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> getProducts(Pageable pageable) {
		return productRepository.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Product getProductById(Long productId) throws EntityNotFoundException {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			throw new EntityNotFoundException("Product with id: " + productId + " does not exists.");
		}
		return product;
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public String deleteProduct(Long productId) throws EntityNotFoundException {
		Product product = this.getProductById(productId);
		productRepository.delete(product);
		return "The product with id: " + productId + " was deleted.";
	}

}
