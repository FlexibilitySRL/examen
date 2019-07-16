package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}
