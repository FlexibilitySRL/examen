package ar.com.flexibility.examen.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;

@Transactional
public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Long> {
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PurchaseOrderLine pol WHERE pol.product = :product")
	boolean existsPurchaseOrderLineByProduct(@Param("product") Product product);
	List<PurchaseOrderLine> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
