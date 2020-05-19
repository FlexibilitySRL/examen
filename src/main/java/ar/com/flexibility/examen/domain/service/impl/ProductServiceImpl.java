package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product create(Product product) throws GenericException {
		logger.info("Creating the product {}", product.toString());

		Product newProduct = productRepository.save(product);

		if (newProduct == null) {
			String msg = String.format("Error during creating the product %s", product);
			logger.error(msg);
			throw new EntityNotSavedException(msg);
		}

		logger.info("Product {} successfully created", newProduct.getId());
		return newProduct;
	}

	@Override
	public Product update(Product product) throws GenericException {
		logger.info("Updating the product {}", product.toString());

		if (!productRepository.existsById(product.getId())) {

			String msg = String.format("Error retrieving the product %s", product.getId());
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		Product updatedProduct = productRepository.save(product);

		if (updatedProduct == null) {
			String msg = String.format("Error during updating the product %s", product);
			logger.error(msg);
			throw new EntityNotUpdatedException(msg);
		}

		logger.info("Product {} successfully updated", product.getId());
		return updatedProduct;
	}

	@Override
	public void delete(Long id) throws GenericException {
		logger.info("Deleging the product {}", id);

		if (!productRepository.existsById(id)) {
			String msg = String.format("Can't find the product %s", id);
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}

		try {
			productRepository.deleteById(id);
			logger.info("Product {} successfully deleted", id);
		} catch (Exception e) {
			String msg = String.format("Error during deleting the product %s \n %s", id, e.getMessage());
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}
	}

	@Override
	public List<Product> getAll() {
		logger.info("Retrieving all products");
		List<Product> result = (List<Product>) productRepository.findAll();
		logger.info("All products result: " + result.toString());
		return result;
	}

	@Override
	public Product getById(Long id) throws GenericException {
		logger.info("Retrieving product {}", id);

		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			String msg = String.format("Error retrieving the product %s", id);
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		logger.info("Returning product: " + product.get().toString());
		return product.get();
	}
}
