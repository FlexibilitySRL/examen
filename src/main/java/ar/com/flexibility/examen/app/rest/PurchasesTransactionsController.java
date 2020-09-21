package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.model.PurchasesTransactions;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import ar.com.flexibility.examen.domain.service.ProcessPurchasesTransactionsService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@RestController
@RequestMapping(path = "/purchases-transactions")
public class PurchasesTransactionsController {
	
	private final Logger LOG = LoggerFactory.getLogger(PurchasesTransactionsController.class);
	
	@Autowired
	private ProcessMessageService messageService;
	
	@Autowired
	@Qualifier("ProcessPurchasesTransactionsServiceImpl")
	private ProcessPurchasesTransactionsService transactionService;	
	
	@GetMapping("/get-purchases-transactions/{id}")
	public ResponseEntity<?> getPurchasesTransactions(@PathVariable(value = "id") Long id){
		PurchasesTransactions purchasesTransactions = new PurchasesTransactions();
		try {
			purchasesTransactions = transactionService.getPurchasesTransactionsById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}
		return ResponseEntity.ok().body(purchasesTransactions);
	}
	
	@GetMapping("/get-all-purchases-transactions")
	public ResponseEntity<?> getAllPurchasesTransactions(){
		List<PurchasesTransactions> listPurchasesTransactions = new ArrayList<>();
		try {
			listPurchasesTransactions = transactionService.getPurchasesTransactions();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}
		return ResponseEntity.ok().body(listPurchasesTransactions);
	}
	
	@PostMapping("/save-purchases-transactions")
	public ResponseEntity<?> savePurchasesTransactions(@RequestBody PurchasesTransactions purchasesTransactions) {
		Message message = new Message(); 
		Boolean resp = false;
		try {
			resp = transactionService.savePurchasesTransactions(purchasesTransactions);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PutMapping("/update-purchases-transactions/{id}")
	public ResponseEntity<?> updatePurchasesTransactions(@PathVariable(value = "id") Long id, @RequestBody PurchasesTransactions purchasesTransactions) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = transactionService.updatePurchasesTransactions(purchasesTransactions, id);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-purchases-transactions/{id}")
	public ResponseEntity<?> deletePurchasesTransactions(@PathVariable(value = "id") Long id) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = transactionService.deletePurchasesTransactions(id);
			if(resp) {
				message = messageService.processMessage("Se ha eliminado la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}						
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}
}
