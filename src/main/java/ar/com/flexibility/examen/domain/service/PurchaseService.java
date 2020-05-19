package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.exception.GenericException;

public interface PurchaseService {

	public Purchase create(Long sellerId, Long clientId) throws GenericException;

	public List<Purchase> getAll();

	public Purchase getById(Long id) throws GenericException ;

	public void addProductTo(Long purchaseId, Long productId) throws GenericException ;

	public void approvePurchase(Long id) throws GenericException ;

}
