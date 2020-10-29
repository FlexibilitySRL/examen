package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.PurchaseTransaction;

@Repository
public interface PurchaseTransactionRepository extends PagingAndSortingRepository<PurchaseTransaction, Long> {

}
