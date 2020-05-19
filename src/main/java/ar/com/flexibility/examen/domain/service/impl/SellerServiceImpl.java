package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

	private static final Logger logger = LogManager.getLogger(SellerServiceImpl.class);

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public Seller create(Seller seller) throws GenericException {
		logger.info("Creating the seller {}", seller.toString());

		Seller newSeller = sellerRepository.save(seller);

		if (newSeller == null) {
			String msg = String.format("Error during creating the seller %s", seller);
			logger.error(msg);
			throw new EntityNotSavedException(msg);
		}

		logger.info("Seller {} successfully created", newSeller.getId());
		return newSeller;
	}

	@Override
	public Seller update(Seller seller) throws GenericException {
		logger.info("Updating the seller {}", seller.toString());

		if (!sellerRepository.existsById(seller.getId())) {

			String msg = String.format("Error retrieving the seller %s", seller.getId());
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		Seller updatedSeller = sellerRepository.save(seller);

		if (updatedSeller == null) {
			String msg = String.format("Error during updating the seller %s", seller);
			logger.error(msg);
			throw new EntityNotUpdatedException(msg);
		}

		logger.info("Seller {} successfully updated", seller.getId());
		return updatedSeller;
	}

	@Override
	public void delete(Long id) throws GenericException {
		logger.info("Deleging the seller {}", id);

		if (!sellerRepository.existsById(id)) {
			String msg = String.format("Can't find the seller %s", id);
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}

		try {
			sellerRepository.deleteById(id);
			logger.info("Seller {} successfully deleted", id);
		} catch (Exception e) {
			String msg = String.format("Error during deleting the seller %s \n %s", id, e.getMessage());
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}
	}

	@Override
	public List<Seller> getAll() {
		logger.info("Retrieving all sellers");
		List<Seller> result = (List<Seller>) sellerRepository.findAll();
		logger.info("All sellers result: " + result.toString());
		return result;
	}

	@Override
	public Seller getById(Long id) throws GenericException {
		logger.info("Retrieving seller {}", id);

		Optional<Seller> seller = sellerRepository.findById(id);
		if (!seller.isPresent()) {
			String msg = String.format("Error retrieving the seller %s", id);
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		logger.info("Returning seller: " + seller.get().toString());
		return seller.get();
	}
}
