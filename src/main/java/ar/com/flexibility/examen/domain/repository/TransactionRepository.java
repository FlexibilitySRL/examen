package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Represents the query repository for {@link Transaction}.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Lists all transaction by transactionId field.
     * @param transactionId field used as filter.
     * @return all transaction by transactionId field.
     */
    List<Transaction> findByTransactionId(Long transactionId);
}
