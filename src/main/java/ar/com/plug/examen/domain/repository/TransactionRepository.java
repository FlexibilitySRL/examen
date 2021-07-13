package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.plug.examen.domain.model.Transaction;

import java.util.List;
/**
 *
 * @author Joan Rey
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.ibanAccount = ?1")
    public List<Transaction> findByIbanAccount(String ibanAccount);

}
