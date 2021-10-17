package ar.com.plug.examen.domain.crud;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrudRepository extends CrudRepository<Customer, String>{
}
