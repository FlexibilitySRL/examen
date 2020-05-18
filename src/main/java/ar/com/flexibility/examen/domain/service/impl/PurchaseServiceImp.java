package ar.com.flexibility.examen.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;

@Service
public class PurchaseServiceImp implements PurchaseService{

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImp.class);
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Override
	public ResponseEntity<?> getPurchases() {
		return new ResponseEntity<>(purchaseRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getPurchaseById(Long id) {
		return new ResponseEntity<>(purchaseRepository.findById(id), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> insertPurchase(Purchase purchase) {
		return  new ResponseEntity<>(purchaseRepository.save(purchase), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> approvePurchase(Long id) {
    	try {
			Purchase purchase = purchaseRepository.findById(id);
	    	purchase.setApprove(true);
	    	purchaseRepository.save(purchase);
    	} catch(NullPointerException ex) {
	    	LOGGER.info(String.format("Compra solicitado: %s, no encontrado", id));
	        return new ResponseEntity<>("Compra no encontrado", HttpStatus.NOT_FOUND);
	    }
		return new ResponseEntity<>("Compra "+ id + " aprovada", HttpStatus.OK);
	}
	
}
