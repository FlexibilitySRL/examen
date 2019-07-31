package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
