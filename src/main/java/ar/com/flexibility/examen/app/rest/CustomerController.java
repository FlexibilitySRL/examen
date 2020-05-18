package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.core.ControllerUtils;
import ar.com.flexibility.examen.app.core.DTOMapper;
import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.impl.CustomerService;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rest controller for the {@link Customer} entity
 *
 */
@RestController
@RequestMapping("v1/customer")
@Log4j
public class CustomerController extends BaseController<Customer, CustomerDTO> {

	private final CustomerService entityService;

	@Autowired
	public CustomerController(CustomerService entityService) {
		this.entityService = entityService;
	}

	/**
	 * Creates a {@link Customer}
	 * @param requestBody a {@link CustomerDTO} representing the request body
	 * @return the created {@link Customer}
	 */
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Customer> create(@RequestBody CustomerDTO requestBody) {
		log.info(String.format("Creating customer: [%s]", requestBody));
		ModelMapper modelMapper = new ModelMapper();
		Customer customer = modelMapper.map(requestBody, Customer.class);
		try {
			customer = entityService.create(customer);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		log.info(String.format("Customer created. [%s]", customer));
		return ResponseEntity.ok(customer);
	}

	/**
	 * Returns a single {@link Customer}
	 * @param customerId the customer id
	 * @return an instance of {@link Customer}
	 * @throws BaseObjectNotFoundException if not found
	 */
	@GetMapping(
			path = "/{customerId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Customer> getById(@PathVariable("customerId") Long customerId) throws BaseObjectNotFoundException {
		log.info(String.format("Searching for customer with id [%d]", customerId));
		Customer customer = entityService.get(customerId);

		log.info(String.format("Returning customer [%s]", customer));
		return ResponseEntity.ok(customer);
	}

	/**
	 * Returns a list of all active {@link Customer}
	 *
	 * @return
	 */
	@GetMapping(
			path = "all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<Customer>> getAll() {
		log.info("Returning all active customers");
		return ResponseEntity.ok(entityService.getAll());
	}

	/**
	 * Logical delete for {@link Customer}
	 * @param customerId the customer id
	 * @return a message if successful
	 * @throws BaseObjectNotFoundException if not found
	 */
	@DeleteMapping(
			path = "/{customerId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<String> delete(@PathVariable("customerId") Long customerId) throws BaseObjectNotFoundException {

		entityService.delete(customerId);

		log.info(String.format("Customer with id [%d] successfully deleted", customerId));
		return ResponseEntity.ok(ControllerUtils.responseMessage("Entity deleted"));
	}

	/**
	 * Update for {@link Customer}
	 * @param id the customer id
	 * @param customerDTO a {@link CustomerDTO} representing the request body
	 * @return the updated {@link Customer}
	 * @throws BaseObjectNotFoundException if not found
	 */
	@PutMapping(
			path = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Customer> update(@NotNull @PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) throws BaseObjectNotFoundException {
		Customer customer = DTOMapper.getInstance().map(customerDTO, Customer.class);

		customer = entityService.update(id, customer);

		log.info(String.format("Object with id [%d] updated [%s]",id, customer));
		return ResponseEntity.ok(customer);
	}
}
