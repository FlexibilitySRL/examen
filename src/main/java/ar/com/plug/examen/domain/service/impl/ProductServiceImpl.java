package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.exception.DuplicateProductException;
import ar.com.plug.examen.exception.NotDataFoundException;
import ar.com.plug.examen.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getAllProduct() {
		List<Product> result = (List<Product>) repository.findAll();
		return result;
	}

	@Override
	public void deleteProduct(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Product createProduct(Product product) {
		Product productCreated = repository.findById(product.getId()).orElse(null);
		if (productCreated != null)
			throw new DuplicateProductException(product.getId());
		return repository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return repository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotDataFoundException(id));
	}

}
