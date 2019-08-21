package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;

@Transactional
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Long> {
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PurchaseOrder po WHERE po.client = :client")
	Boolean existsByClient(@Param("client") Client client);
}
