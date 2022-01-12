package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

}
