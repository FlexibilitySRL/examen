package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerApi> findById(Long customerId);

    CustomerApi getCustomerById(Long customerId) throws ChallengeException;

    CustomerApi createCustomer(CustomerApi customerApi);

    List<CustomerApi> listAllCustomers();

    void removeCustomer(Long customerId) throws NotFoundException;

    CustomerApi updateCustomer(Long customerId, CustomerApi customerApi) throws NotFoundException;
}
