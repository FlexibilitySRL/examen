package ar.com.plug.examen.app.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.exception.CustomerNotFoundException;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	CustomerRepository repository;

	@GetMapping("/")
	CollectionModel<EntityModel<Customer>> all() {
		List<EntityModel<Customer>> customers = repository.findAll().stream()
				.map(customer -> EntityModel.of(customer,
						linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());

	}

	@PostMapping("/")
	EntityModel<Customer> newCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = repository.save(customer);

		return EntityModel.of(savedCustomer,
				linkTo(methodOn(CustomerController.class).one(savedCustomer.getId())).withSelfRel());

	}

	@GetMapping("/{id}")
	EntityModel<Customer> one(@PathVariable long id) {
		Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		return EntityModel.of(customer, linkTo(methodOn(CustomerController.class).one(id)).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("customers"));

	}

	@PutMapping("/{id}")
	EntityModel<Customer> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable long id) {
		return repository.findById(id).map(customer -> {
			customer.setCompanyName(newCustomer.getCompanyName());
			customer.setTaxIdNumber(newCustomer.getTaxIdNumber());
			customer.setEmail(newCustomer.getEmail());
			customer.setPhoneNumber(newCustomer.getPhoneNumber());

			Customer savedCustomer = repository.save(customer);

			return EntityModel.of(savedCustomer,
					linkTo(methodOn(CustomerController.class).one(savedCustomer.getId())).withSelfRel(),
					linkTo(methodOn(CustomerController.class).all()).withRel("customers"));

		}).orElseGet(() -> {
			newCustomer.setId(id);
			Customer savedCustomer = repository.save(newCustomer);

			return EntityModel.of(savedCustomer,
					linkTo(methodOn(CustomerController.class).one(savedCustomer.getId())).withSelfRel(),
					linkTo(methodOn(CustomerController.class).all()).withRel("customers"));

		});
	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteCustomer(@PathVariable long id) {		
		repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
