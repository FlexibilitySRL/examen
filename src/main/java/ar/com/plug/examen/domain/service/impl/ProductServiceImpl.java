package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;

	@Override
	public List<ProductApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(productRepository.findAll(), ProductApi.class);
	}

	@Override
	public ProductApi findById(Long id) throws NotFoundException {
		try {
			logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
			return converter.convert(productRepository.findById(id).get(), ProductApi.class);
		} catch (NoSuchElementException | IllegalArgumentException nse) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Product");
			logger.error(errorMsg);
            throw new NotFoundException(errorMsg);
		}
	}

	@Override
	public List<ProductApi> findByName(String name) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(productRepository.findByName(name), ProductApi.class);
	}
	
	@Override
	public ProductApi save(ProductApi product) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateProduct(product, false);

		Product persisted = productRepository.save(converter.convert(product, Product.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_CREATED, "Product", persisted.getId()); 
		logger.info(successMsg);

		return converter.convert(persisted, ProductApi.class);
	}


    @Override
    public void deleteById(Long id) throws NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
    	this.validateExistence(id);
    	
        productRepository.deleteById(id);
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_DELETED, "Product"); 
		logger.info(successMsg);
    }

	@Override
	public ProductApi update(ProductApi product) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateProduct(product, true);
		this.validateExistence(product.getId());

		Product updated = productRepository.save(converter.convert(product, Product.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_UPDATED, "Product", updated.getId()); 
		logger.info(successMsg);

		return converter.convert(updated, ProductApi.class);
	}
    
	/** VALIDATORS **/
	private void validateExistence(Long id) throws NotFoundException {
		/** Validates the existence of the product to be deleted **/
        if (!productRepository.findById(id).isPresent()) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Product");
			logger.error(errorMsg);
            throw new NotFoundException(errorMsg);
        }
    }
	
}