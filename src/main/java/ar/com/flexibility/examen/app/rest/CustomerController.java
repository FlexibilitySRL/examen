package ar.com.flexibility.examen.app.rest;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import ar.com.flexibility.examen.domain.base.BaseResponse;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, customerService.findAll());
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, customerService.findById(id));
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CustomerDTO dto) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, customerService.save(dto));
		logger.info("Cliente creado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CustomerDTO dto, @PathVariable Long id) {
		dto.setId(id);
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, customerService.save(dto));
		logger.info("Cliente actualizado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("deleted", customerService.delete(id));
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, result);
		logger.info("Cliente eliminado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

}
