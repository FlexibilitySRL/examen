package ar.com.flexibility.examen.domain.service;

import org.springframework.http.ResponseEntity;

import ar.com.flexibility.examen.domain.model.Purchase;


public interface PurchaseService {

	ResponseEntity<?> getPurchases();
	
	ResponseEntity<?> getPurchaseById(Long id);

	ResponseEntity<?> insertPurchase(Purchase purchase);
	
	ResponseEntity<?> approvePurchase(Long id);

}
