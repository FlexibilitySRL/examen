package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.execptions.NotFoundException;

public interface SellerService {
	
	SellerApi createSeller(SellerApi sellerApi);

	SellerApi getSellerById(Long id) throws NotFoundException;

	void removeSeller(Long id) throws NotFoundException;
	
	SellerApi updateSeller(Long id, SellerApi sellerApi) throws NotFoundException;

}
