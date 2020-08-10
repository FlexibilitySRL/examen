package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Sale;

public interface SaleService {

	void approveSale(String code) throws ServiceException;
	
	Sale getEntity(String code) throws ServiceException;

	SaleApiResponse getSale(String code) throws ServiceException;

	List<SaleApiResponse> getSalesByStatus(String status) throws ServiceException;
	
	List<SaleApiResponse> list() throws ServiceException;

	void newSale(String code, String clientIdentifier, String sellerIdentifier, String productCode, int productAmount) throws ServiceException;

}
