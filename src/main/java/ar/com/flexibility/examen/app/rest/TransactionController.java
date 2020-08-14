package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.rest.dto.TransactionRequestDto;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.service.TransactionService;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping(value = "/findTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> findTransaction(@RequestParam Integer transactionId) { 
		return new ResponseEntity<Transaction>(transactionService.findTransaction(transactionId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/createTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequestDto dto) {
		transactionService.createTransaction(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/approveTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> approveTransaction(@RequestBody TransactionRequestDto dto) {
		transactionService.approveTransaction(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
