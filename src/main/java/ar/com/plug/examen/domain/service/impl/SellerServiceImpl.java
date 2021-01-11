package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repositories.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.validations.PairResult;
import ar.com.plug.examen.domain.validations.Validation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private SellerMapper sellerMapper;
	
	@Autowired
	private Validation validation;

	@Override
	public SellerApi createSeller(SellerApi sellerApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateSeller(sellerApi);
		
		if(!result.isValid()) {
			log.error("Mandatory data is missing: " + result.getLeyend());
			throw new BadRequestException("Mandatory data is missing: " + result.getLeyend());
		}

		Seller seller = sellerRepository.save(sellerMapper.fillEntity(new Seller(), sellerApi));

		log.info("The seller " + seller.getId() +" was succesfully created.");
		
		return sellerMapper.getDto(seller);
		
	}

	@Override
	public SellerApi getSellerById(Long id) {
		
		Seller seller = sellerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("The seller with the id:" + id + " was not found."));
		
		return sellerMapper.getDto(seller);
	}

	@Override
	public List<SellerApi> listAllSellers() {
		
		List<Seller> sellers = sellerRepository.findAll();
		
		if(sellers.isEmpty()) {
			log.info("The list of sellers is empty");
		}
		
		return sellerMapper.getDto(sellers);
		
	}

	@Override
	public void removeSeller(Long id) {
		
		if(!sellerRepository.existsById(id)) {
			log.error("The seller with the id:" + id + " does not exist.");
			throw new NotFoundException("The seller with id " + id + " does not exist");
		}
		
		sellerRepository.deleteById(id);
		
		log.info("The seller " + id +" was succesfully deleted.");
		
	}

	@Override
	public SellerApi updateSeller(Long id, SellerApi sellerApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateSeller(sellerApi);
		
		if(!sellerRepository.existsById(id)) {
			log.error("The seller with the id:" + id + " does not exist.");
			throw new NotFoundException("seller with id " + id + " does not exist");
		}else if(!result.isValid()) {
			log.error("Mandatory data is missing: " + result.getLeyend());
			throw new BadRequestException("Mandatory data is missing: " + result.getLeyend());
		} 
		
		validation.validateId(id, sellerApi.getId());
		
		Seller seller = sellerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("The seller with the id:" + id + " was not found."));
		
		seller.setName(sellerApi.getName());
		
		seller = sellerRepository.save(sellerMapper.fillEntity(new Seller(), sellerMapper.getDto(seller)));
		
		
		log.info("The seller " + id +" was succesfully updated.");
		
		return sellerMapper.getDto(seller);
		
	}

}











