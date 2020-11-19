package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
@Transactional
public class SellerServiceImpl implements SellerService {

	private final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;

	@Override
	public List<SellerApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(sellerRepository.findAll(), SellerApi.class);
	}

	@Override
	public SellerApi findById(Long id) throws NotFoundException {
		try {
			logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
			return converter.convert(sellerRepository.findById(id).get(), SellerApi.class);
		} catch (NoSuchElementException | IllegalArgumentException nse) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Seller");
			logger.error(errorMsg);
			throw new NotFoundException(errorMsg);
		}
	}

	@Override
	public List<SellerApi> findByName(String name) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(sellerRepository.findByName(name), SellerApi.class);
	}

	@Override
	public SellerApi save(SellerApi seller) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateSeller(seller, false);

		Seller persisted = sellerRepository.save(converter.convert(seller, Seller.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_CREATED, "Seller", persisted.getId());
		logger.info(successMsg);

		return converter.convert(persisted, SellerApi.class);
	}

	@Override
	public void deleteById(Long id) throws NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		this.validateExistence(id);

		sellerRepository.deleteById(id);
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_DELETED, "Seller");
		logger.info(successMsg);
	}

	@Override
	public SellerApi update(SellerApi seller) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateSeller(seller, true);
		this.validateExistence(seller.getId());

		Seller updated = sellerRepository.save(converter.convert(seller, Seller.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_UPDATED, "Seller", updated.getId());
		logger.info(successMsg);

		return converter.convert(updated, SellerApi.class);
	}

	/** VALIDATORS **/
	private void validateExistence(Long id) throws NotFoundException {
		/** Validates the existence of the seller to be deleted **/
		if (!sellerRepository.findById(id).isPresent()) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Seller");
			logger.error(errorMsg);
			throw new NotFoundException(errorMsg);
		}
	}
}