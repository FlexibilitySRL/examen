package ar.com.plug.examen.domain.service;

import java.util.ArrayList;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    
    public ArrayList<Customer> obtenerCustomers(){
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer crearCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer modificarCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public boolean eliminarCustomer(Long dni) {
        try{
            customerRepository.deleteById(dni);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}