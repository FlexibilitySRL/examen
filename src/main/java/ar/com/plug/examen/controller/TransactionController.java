package ar.com.plug.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.dao.entities.Transaction;
import ar.com.plug.examen.domain.service.ITransactionService;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import ar.com.plug.examen.dto.TransactionDto;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionServiceImpl service;
	
	// Gets every stored transaction
	@GetMapping("/all")
	public ResponseEntity<List<TransactionDto>> getTransactions(){
		return new ResponseEntity<List<TransactionDto>>(service.findAll(), HttpStatus.OK);
	}
	
	
	// Gets a specific client from an id
	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") Long id) throws Exception{
		return new ResponseEntity<TransactionDto>(service.findById(id), HttpStatus.OK);
	}
	
	
	// Stores a new transaction
	@PostMapping("/create")
	public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transaction){
		return new ResponseEntity<TransactionDto>(service.save(transaction), HttpStatus.OK);
	}
}
