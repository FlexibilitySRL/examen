package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
public class SellerServiceImpl implements SellerService {

	private final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

	private final static String ENTITY = "Seller";

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;
	
	/**
	 * @return The complete list of existent sellers
	 */
	@Override
	@Transactional
	public List<SellerApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<SellerApi> result = converter.convertList(sellerRepository.findAll(), SellerApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a seller by its id
	 * @return A specific seller
	 */
	@Override
	@Transactional
	public SellerApi findById(long id) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		SellerApi result = converter.convert(sellerRepository.findOneById(id), SellerApi.class);
		if (Objects.isNull(result))
			throw NotFoundException.unableToFindException(ENTITY);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a seller by its name
	 * Search is case insensitive and matches partially
	 * @return The list of seller which names matches a given string
	 */
	@Override
	@Transactional
	public List<SellerApi> findByName(String name) {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<SellerApi> result = converter.convertList(sellerRepository.findByName(name), SellerApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Persists a new seller
	 * @return Saved seller
	 */
	@Override
	@Transactional
	public SellerApi save(SellerApi seller) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(seller, true);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);
		
		logger.info(String.format(Messages.MSG_PREPARING_PERSISTENCE, ENTITY));
		Seller persisted = sellerRepository.save(converter.convert(seller, Seller.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_CREATED, ENTITY, persisted.getId()));

		return converter.convert(persisted, SellerApi.class);
	}
	
	/**
	 * Removes an existing seller by its id
	 */
	@Override
	@Transactional
	public void deleteById(long id) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(id, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

    	logger.info(String.format(Messages.MSG_PREPARING_DELETION, ENTITY));
    	if (!sellerRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	sellerRepository.deleteById(id);
    	logger.info(String.format(Messages.MSG_SUCCESSFULLY_DELETED, ENTITY)); 
	}
    
    /**
	 * Searches an existing seller by its id and updates it
	 * @return The updated seller
	 */
	@Override
	@Transactional
	public SellerApi update(SellerApi seller) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(seller, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		logger.info(String.format(Messages.MSG_PREPARING_UPDATE, ENTITY));
		if (!sellerRepository.existsById(seller.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Seller updated = sellerRepository.save(converter.convert(seller, Seller.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_UPDATED, ENTITY, updated.getId()));
		return converter.convert(updated, SellerApi.class);
	}
}