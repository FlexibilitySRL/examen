package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>, JpaSpecificationExecutor<OrderItemsRepository>
{
}
