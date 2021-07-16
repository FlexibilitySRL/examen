package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;
import ar.com.plug.examen.domain.service.ITransactionRepo;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Transaction;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private ITransactionRepo transactionService;
	
	/**
	 * Find all transactions
	 * @return List<TransactionDTO>
	 */
	@GetMapping("/")
	public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
		return new ResponseEntity<List<TransactionDTO>>(this.transactionService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Find transaction by id
	 * @param id -> Long
	 * @return TransactionDTO
	 * @throws ResourceNotFoundError
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TransactionDTO> getTransactionByID(@PathVariable long id) throws ResourceNotFoundError {
		return new ResponseEntity<TransactionDTO>(this.transactionService.getTransactionByID(id), HttpStatus.OK);
	}
	

	/**
	 * Save a transaction.
	 * @param request -> TransactionDTORequest
	 * @return TransactionDTO
	 * @throws BadRequestError 
	 * @throws ResourceNotFoundError 
	 */
	@PostMapping("/")
	public ResponseEntity<TransactionDTO> save(@RequestBody TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError {
		return new ResponseEntity<TransactionDTO>(this.transactionService.save(request), HttpStatus.CREATED);
	}
	
	
	/**
	 * Reject transaction by sending id
	 * @param id
	 * @return
	 * @throws ResourceNotFoundError
	 */
	@PutMapping("/{id}/reject")
	public ResponseEntity<?> rejectStatus(@PathVariable Long id) throws ResourceNotFoundError {
		this.transactionService.rejectStatus(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Approve transaction by sending id
	 * @param id
	 * @return
	 * @throws ResourceNotFoundError
	 */
	@PutMapping("/{id}/approve")
	public ResponseEntity<?> approveStatus(@PathVariable Long id) throws ResourceNotFoundError {
		this.transactionService.approveStatus(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Delete a transaction by id
	 * @param id -> long
	 * @return void
	 * @throws ResourceNotFoundError
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) throws ResourceNotFoundError {
		this.transactionService.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
