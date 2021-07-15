package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
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
	 * Save one client.
	 * @param request -> Client
	 * @return Client
	 * @throws BadRequestError 
	 * @throws ResourceNotFoundError 
	 */
	@PostMapping("/")
	public ResponseEntity<TransactionDTO> save(@RequestBody TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError {
		return new ResponseEntity<TransactionDTO>(this.transactionService.save(request), HttpStatus.CREATED);
	}
}
