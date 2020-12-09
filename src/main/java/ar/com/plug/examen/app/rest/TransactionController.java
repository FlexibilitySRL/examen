package ar.com.plug.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.PurchaseApprovalApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

@RestController
public class TransactionController {

	private final TransactionService transactionService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public TransactionController(TransactionServiceImpl transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping(path = "/transaction/{idTransaction}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TransactionApi> findTransaction(@PathVariable Long idTransaction) throws NotExistException {
		TransactionApi transactionApi = transactionService.find(idTransaction);
		logger.info("Se obtuvo la compra correctamente");

		return new ResponseEntity<>(transactionApi, HttpStatus.OK);
	}

	@PostMapping(path = "/purchaseApproval", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTransaction(@RequestBody PurchaseApprovalApi purchaseApprovalApi) throws ValidatorException, NotExistException {
		Long id = transactionService.create(purchaseApprovalApi);
		logger.info("Se aprobo la compra correctamente");
		return new ResponseEntity<>("Se aprobo la compra correctamente, con el identificador: "+id, HttpStatus.OK);
	}
}
