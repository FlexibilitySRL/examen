package ar.com.flexibility.examen.domain.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ar.com.flexibility.examen.domain.model.db.Product;
import ar.com.flexibility.examen.domain.model.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product updateProduct(Product product) {
		getProductById(product.getId());
		return productRepository.saveAndFlush(product);
	}

	@Override
	@Transactional
	public void deleteProduct(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new ProductException("El identificador esta vacío");
		}
		Long identifier = Long.valueOf(id);
		Product product = getProductById(identifier);
		product.setState(false);
		productRepository.saveAndFlush(product);
	}

	@Override
	public Product getProductById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		throw new ProductException("No existe producto");
	}

}
