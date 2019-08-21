package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseTransaction;

@Transactional
public interface PurchaseTransactionRepository extends PagingAndSortingRepository<PurchaseTransaction, Long> {
	PurchaseTransaction findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
