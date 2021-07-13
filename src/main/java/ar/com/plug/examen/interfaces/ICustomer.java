package ar.com.plug.examen.interfaces;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Customer;

public interface ICustomer extends CrudRepository<Customer,Integer> {

}
