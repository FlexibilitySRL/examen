package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.service.TransactionService;


@RestController
@RequestMapping(path="/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TransactionApi> createTransaction(@RequestBody TransactionApi TransactionApi){
		
		return new ResponseEntity<>(transactionService.createTransaction(TransactionApi), HttpStatus.CREATED);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<TransactionApi>> listAllTransactions(){
		
		return new ResponseEntity<>(transactionService.listAllTransactions(), HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<TransactionApi>> getTransactionBySellerId(@PathVariable Long id){
		
		return new ResponseEntity<>(transactionService.getTransactionBySellerId(id), HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionApi> updateTransaction(@PathVariable Long id, @RequestParam String validation){
		
		return new ResponseEntity<>(transactionService.approveTransaction(id, validation), HttpStatus.CREATED);
		
	}
	
}
