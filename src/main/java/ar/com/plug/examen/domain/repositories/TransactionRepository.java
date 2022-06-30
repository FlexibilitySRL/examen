package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long id);

    List<Transaction> findBySellerId(Long sellerId);

}
