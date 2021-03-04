package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.Repository.SellerRepository;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.service.ValidatorService;

@Service
public class SellerServiceImpl implements SellerService{
	private final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

	private final static String ENTITY = "Seller";

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorService validators;

	/**
	 * The complete list of existent sellers
	 * @return List<SellerApi>
	 */
	@Override
	@Transactional
	public List<SellerApi> listAll() {
		logger.info("Searching requested data...");
		List<SellerApi> result = converter.convertList(sellerRepository.findAll(), SellerApi.class);
		logger.info("Found!");
		return result;
	}

	/**
	 * Searches a seller by its id
	 * @return SellerApi seller
	 */
	@Override
	@Transactional
	public SellerApi findById(long id) throws NotFoundException {
		try {
			logger.info("Searching requested data...");
			SellerApi result = converter.convert(sellerRepository.findById(id).get(), SellerApi.class);
			logger.info("Found!");
			return result;
		} catch (NoSuchElementException iae) {
			throw NotFoundException.unableToFindException(ENTITY);
		}
	}

	/**
	 * Searches a seller by its name
	 * Search in case insensitive and matches partially
	 * Returns the list of seller which names matches a given string
	 * @param String name
	 * @return List<SellerApi>
	 */
	@Override
	@Transactional
	public List<SellerApi> findByName(String name) {
		logger.info("Searching requested data...");
		List<SellerApi> result = converter.convertList(sellerRepository.findByName(name), SellerApi.class);
		logger.info("Found!");
		return result;
	}

	/**
	 * Persists a new seller and returns it
	 * @param SellerApi
	 * @return SellerApi
	 */
	@Override
	@Transactional
	public SellerApi save(SellerApi seller) throws BadRequestException {
		logger.info("Validating requeseted data...");
		validators.checkCompleteObject(seller, true);
		logger.info("Validation successful!");

		logger.info(String.format("Preparing for persistence of a %1$s ..." , ENTITY));
		Seller persisted = sellerRepository.save(converter.convert(seller, Seller.class));
		logger.info(String.format("%1$s successfully created with id: %2$s!", ENTITY, persisted.getId()));

		return converter.convert(persisted, SellerApi.class);
	}

	/**
	 * Removes an existing seller by its id
	 */
	@Override
	@Transactional
	public void deleteById(long id) throws NotFoundException {
    	logger.info(String.format("Preparing for deletion of a %1$s ...", ENTITY));
    	if (!sellerRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	sellerRepository.deleteById(id);
    	logger.info(String.format("%1$s successfully deleted!", ENTITY)); 
	}

    /**
	 * Searches an existing seller by its id and updates it
	 * @param SellerApi
	 * @return SellerApi
	 */
	@Override
	@Transactional
	public SellerApi update(SellerApi seller) throws NotFoundException, BadRequestException {
		logger.info("Validating provided data");
		validators.checkCompleteObject(seller, false);
		logger.info("Validation successful!");

		logger.info(String.format("Preparing for update of a %1$s ...", ENTITY));
		if (!sellerRepository.existsById(seller.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Seller updated = sellerRepository.save(converter.convert(seller, Seller.class));
		logger.info(String.format("%1$s with id: %2$s - successfully updated!", ENTITY, updated.getId()));
		return converter.convert(updated, SellerApi.class);
	}

}
