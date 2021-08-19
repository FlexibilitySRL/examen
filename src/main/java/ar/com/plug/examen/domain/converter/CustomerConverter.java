package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.domain.dto.Customer;
import ar.com.plug.examen.domain.model.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends Converter<Customer, CustomerModel> {
    public CustomerConverter() {
        super(CustomerConverter::convertToEntity, CustomerConverter::convertToModel);
    }

    private static Customer convertToModel(CustomerModel customerModel) {
        return new Customer(customerModel.getId(), customerModel.getName(), customerModel.getIdStatus());
    }

    private static CustomerModel convertToEntity(Customer dto) {
        return new CustomerModel(dto.getId(), dto.getName(), dto.getIdStatus());
    }

}
