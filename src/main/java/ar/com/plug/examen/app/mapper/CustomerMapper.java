package ar.com.plug.examen.app.mapper;

import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO asDTO(CustomerApi source) {
        return new CustomerDTO(null, source.getName());
    }

    public CustomerDTO asDTO(Customer source) {
        return new CustomerDTO(source.getId(), source.getName());
    }

}