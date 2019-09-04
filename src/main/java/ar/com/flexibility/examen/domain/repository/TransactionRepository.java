package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
