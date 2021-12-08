package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerService {
	
	public List<Customer> consultar();
	public void crear(Customer customer);
	public void editar(Customer customer);
}
