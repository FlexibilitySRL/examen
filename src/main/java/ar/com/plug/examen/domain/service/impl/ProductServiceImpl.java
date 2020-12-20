package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.exception.ProductNotFoundException;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Product getOne(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product add(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product modify(Product product) {
		productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getId()));
		return productRepository.save(product);
	}

	@Override
	public void delete(long id) {
		productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		productRepository.deleteById(id);
	}

}
