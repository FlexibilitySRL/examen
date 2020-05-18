package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link PurchaseOrder}
 */
@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
	List<PurchaseOrder> findAllByCustomer(Long id);
}
