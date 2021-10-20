package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {

    CustomerDTO save(CustomerDTO customer);
    CustomerDTO update(CustomerDTO customer);
    void delete(String customerId);

    Optional<CustomerDTO> getById(String customerId);
    List<CustomerDTO> getAll();

}
