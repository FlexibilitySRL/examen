package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
