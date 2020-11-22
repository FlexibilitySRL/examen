package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final static String ENTITY = "Product";

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;

	/**
	 * @return The complete list of existent products
	 */
	@Override
	@Transactional
	public List<ProductApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<ProductApi> result = converter.convertList(productRepository.findAll(), ProductApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a product by its id
	 * @return A specific product
	 */
	@Override
	@Transactional
	public ProductApi findById(long id) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		ProductApi result = converter.convert(productRepository.findOneById(id), ProductApi.class);
		if (Objects.isNull(result))
			throw NotFoundException.unableToFindException(ENTITY);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a product by its name
	 * Search is case insensitive and matches partially
	 * @return The list of product which names matches a given string
	 */
	@Override
	@Transactional
	public List<ProductApi> findByName(String name) {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<ProductApi> result = converter.convertList(productRepository.findByName(name), ProductApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}
	
	/**
	 * Persists a new product
	 * @return Saved product
	 */
	@Override
	@Transactional
	public ProductApi save(ProductApi product) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(product, true);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		logger.info(String.format(Messages.MSG_PREPARING_PERSISTENCE, ENTITY));
		Product persisted = productRepository.save(converter.convert(product, Product.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_CREATED, ENTITY, persisted.getId()));

		return converter.convert(persisted, ProductApi.class);
	}
	
	/**
	 * Removes an existing product by its id
	 */
    @Override
    @Transactional
    public void deleteById(long id) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(id, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

    	logger.info(String.format(Messages.MSG_PREPARING_DELETION, ENTITY));
    	if (!productRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	productRepository.deleteById(id);
    	logger.info(String.format(Messages.MSG_SUCCESSFULLY_DELETED, ENTITY)); 
    }
    
    /**
	 * Searches an existing product by its id and updates it
	 * @return The updated product
	 */
	@Override
	@Transactional
	public ProductApi update(ProductApi product) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(product, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		logger.info(String.format(Messages.MSG_PREPARING_UPDATE, ENTITY));
		if (!productRepository.existsById(product.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Product updated = productRepository.save(converter.convert(product, Product.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_UPDATED, ENTITY, updated.getId()));
		return converter.convert(updated, ProductApi.class);
	}
	
}