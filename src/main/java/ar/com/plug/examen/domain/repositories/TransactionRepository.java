package ar.com.plug.examen.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	Optional<Transaction> findById(Long id);
	
	List<Transaction> findBySellerId(Long sellerId);
	
}
