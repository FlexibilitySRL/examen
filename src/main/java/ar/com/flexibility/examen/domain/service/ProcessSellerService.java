package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.SellerApi;

public interface ProcessSellerService {

	SellerApi create(SellerApi sellerApi);
	
	SellerApi update(Long sellerId, SellerApi sellerApi);
	
	String delete(SellerApi sellerApi);
	
	SellerApi searchByName(String name);
}
