package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.Customer;

public interface ProcessCustomerService {

    Customer save(Customer customer);

    void updateActive(Long id, Boolean active) ;
}
