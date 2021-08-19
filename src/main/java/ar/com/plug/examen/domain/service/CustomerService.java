package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.objects.JsonResponseTransaction;

public interface CustomerService {
    JsonResponseTransaction addCustomer(CustomerModel customerModel);
    JsonResponseTransaction deleteCustomer(Long id);
    JsonResponseTransaction updateCustomer(CustomerModel customerModel);
    CustomerModel findCustomerModelById(Integer id);
}
