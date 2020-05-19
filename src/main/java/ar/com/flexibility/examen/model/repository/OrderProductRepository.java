package ar.com.flexibility.examen.model.repository;

import ar.com.flexibility.examen.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository  extends CrudRepository<OrderProduct, Long> {
}
