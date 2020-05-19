package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.exception.GenericException;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@GetMapping("/")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Purchase>> findAll() {
		return ResponseEntity.ok(purchaseService.getAll());
	}

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Purchase> find(@PathVariable Long id) throws GenericException {
		return ResponseEntity.ok(purchaseService.getById(id));
	}

	@PostMapping("/new/{sellerId}/{clientId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Long> createPurchase(@PathVariable Long sellerId, @PathVariable Long clientId)
			throws GenericException {
		return ResponseEntity.ok(purchaseService.create(sellerId, clientId).getId());
	}

	@PutMapping("/addProduct/{purchaseId}/{productId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void addProduct(@PathVariable Long purchaseId, @PathVariable Long productId) throws GenericException {
		purchaseService.addProductTo(purchaseId, productId);
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void approve(@PathVariable Long id) throws GenericException {
		purchaseService.approvePurchase(id);
	}
}
