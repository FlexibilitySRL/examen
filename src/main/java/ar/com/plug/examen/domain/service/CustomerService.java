package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerService {
	public abstract Customer getOne(long id); 
	public abstract List<Customer> getAll();
	public abstract Customer add(Customer customer);
	public abstract Customer modify(Customer customer); 
	public abstract void delete (long id);
}
