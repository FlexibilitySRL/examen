package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.Customer;

public interface ProcessCustomerService {

    Customer save(Long id, String name, Boolean active);

    Customer findById(Long id);
}
