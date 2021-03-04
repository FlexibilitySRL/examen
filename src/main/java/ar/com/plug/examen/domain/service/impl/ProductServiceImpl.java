package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.Repository.ProductRepository;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.ValidatorService;

@Service
public class ProductServiceImpl implements ProductService{
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final static String ENTITY = "Product";

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorService validators;

	/**
	 * The complete list of existent products
	 * @Return List<ProductApi> 
	 */
	@Override
	@Transactional
	public List<ProductApi> listAll() {
		logger.info("Searching requested data...");
		List<ProductApi> result = converter.convertList(productRepository.findAll(), ProductApi.class);
		logger.info("Found!");
		return result;
	}

	/**
	 * Searches a product by its id
	 * @param long id
	 * @return ProductApi
	 */
	@Override
	@Transactional
	public ProductApi findById(long id) throws NotFoundException {
		try {
			logger.info("Searching requested data...");
			ProductApi result = converter.convert(productRepository.findById(id).get(), ProductApi.class);
			logger.info("Found");
			return result;
		} catch (NoSuchElementException iae) {
			throw NotFoundException.unableToFindException(ENTITY);
		}
	}

	/**
	 * Searches a product by its name
	 * Search is case insensitive and matches partially
	 * Returns the list of product which names matches a given string
	 * @param String name
	 * @return List<ProductApi>
	 */
	@Override
	@Transactional
	public List<ProductApi> findByName(String name) {
		logger.info("Searching requested data...");
		List<ProductApi> result = converter.convertList(productRepository.findByName(name), ProductApi.class);
		logger.info("Found!");
		return result;
	}

	/**
	 * Persists a new product
	 * @return ProductApi
	 */
	@Override
	@Transactional
	public ProductApi save(ProductApi product) throws BadRequestException {
		logger.info("Validating provided data...");
		validators.checkCompleteObject(product, true);
		logger.info("Validation successful!");

		logger.info(String.format("Preparing for persistence of a %1$s ...", ENTITY));
		Product persisted = productRepository.save(converter.convert(product, Product.class));
		logger.info(String.format("%1$s successfully created with id: %2$s!\r\n", ENTITY, persisted.getId()));

		return converter.convert(persisted, ProductApi.class);
	}

	/**
	 * Removes an existing product by its id
	 * @param long id
	 */
    @Override
    @Transactional
    public void deleteById(long id) throws NotFoundException {
    	logger.info(String.format("Preparing for deletion of a %1$s ...", ENTITY));
    	if (!productRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	productRepository.deleteById(id);
    	logger.info(String.format("%1$s successfully deleted!", ENTITY)); 
    }

    /**
	 * Searches an existing product by its id and updates it
	 * @return The updated product
	 */
	@Override
	@Transactional
	public ProductApi update(ProductApi product) throws NotFoundException, BadRequestException {
		logger.info("Validating provided data...");
		validators.checkCompleteObject(product, false);
		logger.info("Validation successful!");

		logger.info(String.format("Preparing for update of a %1$s ...", ENTITY));
		if (!productRepository.existsById(product.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Product updated = productRepository.save(converter.convert(product, Product.class));
		logger.info(String.format("%1$s with id: %2$s - successfully updated!", ENTITY, updated.getId()));
		return converter.convert(updated, ProductApi.class);
	}

}
