package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.SellerDTO;
import ar.com.plug.examen.domain.repositories.SellerRepository;
import ar.com.plug.examen.domain.service.ISellerRepo;
import ar.com.plug.examen.domain.validators.Validator;

@Service
public class SellerServiceImpl implements ISellerRepo{

	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private SellerMapper sellerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	@Override
	public SellerDTO findSellerByID(long id) throws ResourceNotFoundError {
		logger.info("Finding seller with ID " + id);
		SellerDTO s = this.sellerMapper.sellerToSellerDTO(this.sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Seller not found")));
		logger.info("Finding seller with ID " + id + ". DONE");
		return s;
	}

	@Override
	public List<SellerDTO> findAll() {
		logger.info("Finding all sellers..");
		List<SellerDTO> sellers = this.sellerMapper.sellerToListSellerDTO(this.sellerRepository.findAll()); 
		logger.info("Finding all sellers DONE");
		return sellers;
	}

	@Override
	@Transactional
	public SellerDTO save(SellerDTO seller) throws BadRequestError {
		logger.info("Validating seller");
		this.validator.validateSeller(seller);
		logger.info("Validating sellerOK");
		logger.info("Saving seller...");
		SellerDTO s = this.sellerMapper.sellerToSellerDTO(this.sellerRepository.save(this.sellerMapper.sellerDTOtoSeller(seller)));
		logger.info("Saving seller DONE");
		return s;
	}

	@Override
	@Transactional
	public SellerDTO update(SellerDTO seller) throws ResourceNotFoundError, BadRequestError {
		logger.info("Validating seller");
		this.validator.validateSeller(seller);
		logger.info("Validating seller DONE");
		logger.info("Finding seller");
		this.sellerRepository.findById(seller.getId()).orElseThrow(() -> new ResourceNotFoundError("Seller not found"));
		logger.info("Finding seller DONE");
		SellerDTO s = this.sellerMapper.sellerToSellerDTO(this.sellerRepository.save(this.sellerMapper.sellerDTOtoSeller(seller)));
		logger.info("seller updated");
		return s;
	}

	@Override
	@Transactional
	public void delete(long id) throws ResourceNotFoundError {
		logger.info("Finding seller by ID: " + id);
		this.sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Seller not found"));
		logger.info("Seller found");
		logger.info("Deleting seller");
		this.sellerRepository.deleteById(id);
		logger.info("Deleting seller DONE");
	}

}
