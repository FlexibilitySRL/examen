package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.plug.examen.domain.InterfacesServices.ICustomerService;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.interfaces.ICustomer;

public class CustomerService implements ICustomerService{
	
	@Autowired
	private ICustomer data;
	

	@Override
	public List<Customer> Listar() {
		// TODO Auto-generated method stub
		return (List<Customer>) data.findAll();	}

	@Override
	public Optional<Customer> ListarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Customer c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
