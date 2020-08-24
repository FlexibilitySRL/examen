package ar.com.flexibility.examen.domain.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.ProcessSellerService;
import ar.com.flexibility.examen.repository.SellerRepository;

@Service
public class ProcessSellerServiceImpl implements ProcessSellerService {

	private final Logger log = LoggerFactory.getLogger(ProcessSellerServiceImpl.class);

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public SellerApi create(SellerApi sellerApi) {
		Seller seller = modelMapper.map(sellerApi, Seller.class);
		seller = sellerRepository.saveAndFlush(seller);
		sellerApi = modelMapper.map(seller, SellerApi.class);
		log.info("Seller successfully created.");
		return sellerApi;
	}

	@Override
	public SellerApi update(Long sellerId, SellerApi sellerApi) {
		Seller seller = modelMapper.map(sellerApi, Seller.class);
		seller = sellerRepository.findById(sellerId);
		seller.setFirstName(sellerApi.getFirstName());
		seller.setLastName(sellerApi.getLastName());
		seller = sellerRepository.saveAndFlush(seller);
		sellerApi = modelMapper.map(seller, SellerApi.class);
		log.info("Seller successfully updated.");
		return sellerApi;
	}

	@Override
	public String delete(SellerApi sellerApi) {
		Seller seller = modelMapper.map(sellerApi, Seller.class);
		sellerRepository.delete(seller);
		log.info("Seller successfully deleted.");
		return "Seller successfully deleted.";
	}
	
	@Override
	public SellerApi searchByName(String name) {
		Seller seller = sellerRepository.findByName(name);
		SellerApi sellerApi = null;
		if(seller != null) {
			sellerApi = modelMapper.map(seller, SellerApi.class);
		}
		return sellerApi;
	}
}
