package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.base.BaseResponse;
import ar.com.flexibility.examen.domain.dto.TransactionDTO;
import ar.com.flexibility.examen.domain.model.TransactionStatus;
import ar.com.flexibility.examen.domain.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, transactionService.findAll());
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, transactionService.findById(id));
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody TransactionDTO dto) {
		if (dto.getStatus() == null) {
			dto.setStatus(TransactionStatus.PENDING);
		}
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, transactionService.save(dto));
		logger.info("Transaccion registrada correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

}
