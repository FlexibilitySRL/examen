package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByTransactionId(Long transactionId);
}
