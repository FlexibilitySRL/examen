package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.OrderApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.exception.ClientNotFoundException;
import ar.com.flexibility.examen.domain.exception.EmptyOrderException;
import ar.com.flexibility.examen.domain.exception.InsufficientBalanceException;
import ar.com.flexibility.examen.domain.exception.NegativeAmountException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductWithoutStock;
import ar.com.flexibility.examen.domain.model.Client;

public interface ClientService {

	List<ProductApi> findAllProducts();
	
	ClientApi saveOrUpdate(Client client);
	
	List<PurchaseApi> findAllPurchaseOfClient(Long idClient) throws ClientNotFoundException;

	void processPurchase(Long idClient, List<OrderApi> orders) throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException;

	void updateBalance(Long idClient, double amount) throws NegativeAmountException, ClientNotFoundException;

	ClientApi getClientApi(Long idClient) throws ClientNotFoundException;
	
}
