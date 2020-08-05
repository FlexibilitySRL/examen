package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerService extends PersonService<SellerApiResponse>{

	Seller getEntity (String identifier) throws ServiceException;
	
}
