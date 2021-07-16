package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
