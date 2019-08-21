package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.dto.PurchaseTransactionDTO;
import ar.com.flexibility.examen.domain.service.PurchaseTransactionService;

public class PurchaseTransactionController {
	@Autowired
	private PurchaseTransactionService purchaseTransactionService;
	
	@GetMapping("purchaseTransaction")
	public ResponseEntity<List<ObjectDTO<PurchaseTransactionDTO>>> listTransactions(@RequestBody PageRequestDTO pageRequestDTO) {
		return new ResponseEntity<List<ObjectDTO<PurchaseTransactionDTO>>>(this.purchaseTransactionService.listTransactions(pageRequestDTO), HttpStatus.OK);
	}
	
	@GetMapping("client/{id}")
	public ResponseEntity<PurchaseTransactionDTO> getPurchaseTransaction(@PathVariable("id") Long id) {
		return new ResponseEntity<PurchaseTransactionDTO>(this.purchaseTransactionService.getTransaction(id), HttpStatus.OK);
	}
}
