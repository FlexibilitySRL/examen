package ar.com.flexibility.examen.app.rest;

import java.util.List;

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

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;

/**
 * 
 * @author hackma
 * @version 0.1
 * Servicio de Clientes 
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	/**
	 * Metodo para la creación de clientes
	 * @param customer
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		log.info("Entrada de metodo {createCustomer}");
		try {
			ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(
					customerService.createCustomer(customer), HttpStatus.OK);
			log.info("Salida de metodo {createCustomer} customer creado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {createCustomer}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para actualizar al cliente por el id
	 * @param customer
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		log.info("Entrada de metodo {updateCustomer}");
		try {
			ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(
					customerService.updateCustomer(customer), HttpStatus.OK);
			log.info("Salida de metodo {updateCustomer} customer actualizado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {updateCustomer}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para eliminar al cliente por el id
	 * @param customer
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer) {
		log.info("Entrada de metodo {deleteCustomer}");
		try {
			ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(
					customerService.deleteCustomer(customer), HttpStatus.OK);
			log.info("Salida de metodo {deleteCustomer} customer eliminado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {deleteCustomer}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para listar a todos los clientes
	 * @return
	 */
	@GetMapping(path = "/")
	public ResponseEntity<?> findAllCustomer() {
		log.info("Entrada de metodo {findAllCustomer}");
		try {
			ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(
					customerService.findAllCustomers(), HttpStatus.OK);
			log.info("Salida de metodo {findAllCustomer} customer encontrados exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {findAllCustomer}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Metodo para encontrar un cliente especifico por el id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable String id) {
		log.info("Entrada de metodo {findCustomerById}");
		try {
			ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.findCustomerById(id),
					HttpStatus.OK);
			log.info("Salida de metodo {findCustomerById} customer encontrado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {findCustomerById}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
