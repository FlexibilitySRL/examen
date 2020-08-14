package ar.com.flexibility.examen.domain.service.impl;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.rest.dto.CustomerRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.exception.CreationException;
import ar.com.flexibility.examen.exception.DataValidationException;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Log log = LogFactory.getLog(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/*
	 * se busca un cliente por su nombre. El nombre ingreado
	 * debe ser igual al registrado.
	 */
	@Override
	public Customer findCustomer(String customerName) {
		Customer customer = customerRepository.findByName(customerName);
		log.error("Se realizó la busqueda del cliente exitosamente");
		
		return customer;
	}

	/*
	 * se crea un nuevo cliente. Se valida que no exista
	 * uno con el mismo nombre ya registrado
	 */
	@Override
	public void createCustomer(CustomerRequestDto dto) {
		Customer customer = customerRepository.findByName(dto.getName());
		if (customer != null) {
			log.error("ya existe un cliente con este nombre, no se puede crear");
			throw new CreationException("ya existe un cliente con este nombre, no se puede crear");
		}
		customer = new Customer();
		customer.setName(dto.getName());
		
		customerRepository.saveAndFlush(customer);
		log.error("El cliente se creó exitosamente");
	}

	/*
	 * se elimina un cliente. Primero se chequea que el mismo este
	 * registrado en la base de datos
	 */
	@Override
	public void deleteCustomer(Integer customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(!customer.isPresent()) {
			log.error("el cliente no existe, no se puede eliminar");
			throw new DataValidationException("el cliente no existe, no se puede eliminar");
		}
		
		customerRepository.delete(customer.get());
		log.error("El cliente se eliminó exitosamente");
	}

	/*
	 * se actualiza el cliente. El unico dato modificable es el nombre.
	 * Se valida que el mismo exista antes de modificarlo
	 */
	@Override
	public void updateCustomer(CustomerRequestDto dto) throws DataValidationException {
		Optional<Customer> customer = customerRepository.findById(dto.getId());
		if(!customer.isPresent()) {
			log.error("el cliente no existe, no se puede actualizar");
			throw new DataValidationException("el cliente no existe, no se puede actualizar");
		}
		customer.get().setName(dto.getName());
		customerRepository.saveAndFlush(customer.get());	
		log.error("El cliente se actualizó exitosamente");
	}
}
