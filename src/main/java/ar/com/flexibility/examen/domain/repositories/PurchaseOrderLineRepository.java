package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;

@Transactional
public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Long> {

}
