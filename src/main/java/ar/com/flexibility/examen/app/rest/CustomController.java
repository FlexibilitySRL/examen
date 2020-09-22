package ar.com.flexibility.examen.app.rest;

import java.util.Objects;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;

@RestController
@RequestMapping(path = "/customer")
public class CustomController {

	private static final Logger logger = LogManager.getLogger(CustomController.class);

	@Autowired
	private ProcessMessageService messageService;

	@Autowired
	private CustomerService customerService;

	@PostMapping("/echo")
	public ResponseEntity<?> echo(@RequestBody MessageApi message) {
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") long id) {
		CustomerDTO customer = customerService.getCustomer(id);
		if (Objects.isNull(customer)) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Customer with id {} found.", id);
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer, BindingResult result) {
		if (result.hasErrors()) {
			logger.error("Customer invalid.", customer);
			return new ResponseEntity<CustomerDTO>(HttpStatus.BAD_REQUEST);
		}
		CustomerDTO customerDB = customerService.createCustomer(customer);
		logger.info("Customer with id {} created.", customerDB.getId());
		return new ResponseEntity<CustomerDTO>(customerDB, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO customer) {
		customer.setId(id);
		CustomerDTO currentCustomer = customerService.updateCustomer(customer);
		if (Objects.isNull(currentCustomer)) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Customer with id {} update.", id);
		return new ResponseEntity<CustomerDTO>(currentCustomer, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") Long id) {
		CustomerDTO customer = customerService.deleteCustomer(id);
		if (Objects.isNull(customer)) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
		logger.info("Customer with id {} deleted.", id);
		return ResponseEntity.ok(customer);
	}
}
