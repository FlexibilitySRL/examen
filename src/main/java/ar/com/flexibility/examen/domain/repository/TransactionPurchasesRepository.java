package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.TransactionPurchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionPurchasesRepository extends JpaRepository<TransactionPurchases, Long> {
    //@Query("SELECT t FROM TransactionPurchases t where t.transactionId = :transactionId")
    Optional<TransactionPurchases> findByTransactionId(String transactionId);
}
