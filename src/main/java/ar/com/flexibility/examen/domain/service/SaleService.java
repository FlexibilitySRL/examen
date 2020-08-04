package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Sale;

public interface SaleService {

	void approveSale(String code) throws ServiceException;

	Sale getSale(String code) throws ServiceException;

	List<Sale> getSalesByStatus(String status) throws ServiceException;
	
	List<Sale> list() throws ServiceException;

	void newSale(String code, String clientIdentifier, String sellerIdentifier, String productCode, int productAmount) throws ServiceException;

}
