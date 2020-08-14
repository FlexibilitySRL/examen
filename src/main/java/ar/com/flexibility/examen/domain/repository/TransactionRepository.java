package ar.com.flexibility.examen.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	public Optional<Transaction> findById(Integer transactionId);
}
