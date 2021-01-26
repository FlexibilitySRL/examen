package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.exception.DeleteCustomerException;
import ar.com.plug.examen.exception.DuplicateCustomerException;
import ar.com.plug.examen.exception.NotCustomerFoundException;
import ar.com.plug.examen.repository.CustomerRepository;
import lombok.Data;

@Service
@Data
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> result = (List<Customer>) repository.findAll();
		return result;
	}

	@Override
	public Customer getCustomerById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotCustomerFoundException(id));
	}

	@Override
	public void deleteCustomer(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DeleteCustomerException(id);
		}
	}

	@Override
	public Customer create(Customer customer) {
		Customer customerDb = repository.findByDocumentId(customer.getDocumentId());
		if (customerDb != null)
			throw new DuplicateCustomerException(customer.getDocumentId());
		return repository.save(customer);
	}

	@Override
	public Customer update(Customer customer) {
		Customer customerDb = repository.findByIdAndDocumentId(customer.getId(), customer.getDocumentId());
		if (customerDb == null)
			throw new NotCustomerFoundException(customer.getId(), customer.getDocumentId());
		return repository.save(customer);
	}

}
