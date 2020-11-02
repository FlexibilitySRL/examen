package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {
	@Autowired
	CustomerRepository customerRepo;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	/**
	 * Creates the customer.
	 *
	 * @param entity the entity
	 * @return the customer
	 */
	public Customer createCustomer(@Valid Customer entity) {
		return customerRepo.save(entity);
	}

	/**
	 * Delete customer.
	 *
	 * @param customerId the customer id
	 */
	public void deleteCustomer(long customerId) {
		try {
			customerRepo.deleteById(customerId);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException();
		}
	}

	/**
	 * Update customer.
	 *
	 * @param customerId the customer id
	 * @param newEntity  the new entity
	 * @return the customer
	 */
	public Customer updateCustomer(long customerId, Customer newEntity) {

		Customer oldEntity = customerRepo.findById(customerId).orElseThrow(EntityNotFoundException::new);

		dtoMapper.updateCustomer(newEntity, oldEntity);

		oldEntity = customerRepo.save(oldEntity);

		return oldEntity;
	}

	/**
	 * Método que busca un único Customer por Id. Si no lo encuentra, levanta una
	 * excepción EntityNotFoundException que luego es mapeada al objeto Error
	 * documentado en la API.
	 * 
	 * @param customerId
	 * @return
	 */
	public Customer getCustomer(long customerId) {
		return customerRepo.findById(customerId).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * Gets the customers.
	 * 
	 * TODO: Admitir filtros de búsqueda TODO: Paginar TODO: Ordenar
	 *
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return ImmutableList.copyOf(customerRepo.findAll());
	}
}
