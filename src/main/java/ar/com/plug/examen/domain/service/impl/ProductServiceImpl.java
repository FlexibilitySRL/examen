package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.exception.DeleteProductException;
import ar.com.plug.examen.exception.DuplicateProductException;
import ar.com.plug.examen.exception.NotProductFoundException;
import ar.com.plug.examen.exception.ProductPriceException;
import ar.com.plug.examen.exception.ProductStockException;
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
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DeleteProductException(id);
		}
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
		return repository.findById(id).orElseThrow(() -> new NotProductFoundException(id));
	}

	@Override
	public void validateProducts(List<Product> products) {
		for (Product product : products) {
			Product productCreated = getProductById(product.getId());
			if (product.getQuantity() > productCreated.getQuantity())
				throw new ProductStockException(product.getName());

			if (!product.getPrice().equals(productCreated.getPrice()))
				throw new ProductPriceException(product.getName());
		}
	}

	@Override
	public void updateQuantityDecrement(List<Product> products) {
		for (Product product : products) {
			Product productCreated = getProductById(product.getId());
			int quantityNew = productCreated.getQuantity() - product.getQuantity();
			productCreated.setQuantity(quantityNew);
			updateProduct(productCreated);
		}
	}

}
