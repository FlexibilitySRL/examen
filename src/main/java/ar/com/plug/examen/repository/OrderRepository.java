package ar.com.plug.examen.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

	public List<Order> findByStatus(String status);
	
}
