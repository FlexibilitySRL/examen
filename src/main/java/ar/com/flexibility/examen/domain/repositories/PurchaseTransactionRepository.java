package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.PurchaseTransaction;

@Transactional
public interface PurchaseTransactionRepository extends CrudRepository<PurchaseTransaction, Long> {

}
