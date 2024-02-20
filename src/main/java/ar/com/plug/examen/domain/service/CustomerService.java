package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer) throws CustomerException;

    public Customer updateCustomer(Customer customer) throws CustomerException;

    public Customer remove(Integer customerId) throws CustomerException;

    public List<Customer> viewAllCustomer() throws CustomerException;
}
