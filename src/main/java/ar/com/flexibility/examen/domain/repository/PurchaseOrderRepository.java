package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
