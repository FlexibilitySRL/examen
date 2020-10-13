package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Map;

import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;

public interface IPurchaseService {
	
	public List<Purchase> findAll();
	
	public Purchase findById(Long id);

	public Purchase changeStatus(Long id, PurchaseStatus status);

	public List<Purchase> bulkChangeStatus(Client client, PurchaseStatus status);
	
	public List<Purchase> findByClient(Client client);
	
	public List<Purchase> findByProduct(Long productId);
	
	public List<Purchase> findByClientAndStatus(Client client, PurchaseStatus status);
	
	public List<Purchase> findBySellerAndStatus(Seller client, PurchaseStatus status);
	
	public Purchase buy(String description, Long clientId, Long sellerId, Map<Long, Integer> productsQuantity);

	public void delete(Long id);
	
}
