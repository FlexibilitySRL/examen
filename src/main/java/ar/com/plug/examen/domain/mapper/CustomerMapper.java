package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerDTO toCustomerDto(Customer customer);
    List<CustomerDTO> toListCustomerDto(List<Customer> customerList);

    @InheritInverseConfiguration
    @Mapping(target = "transactions", ignore = true)
    Customer toCustomer(CustomerDTO customerDTO);

}
