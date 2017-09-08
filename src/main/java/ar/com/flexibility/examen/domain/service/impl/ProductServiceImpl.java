package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.exists(product.getId()) ? productRepository.save(product) : null;
	}

	@Override
	public Product deleteProduct(Product product) {
		productRepository.delete(product);
		return product;
	}
}