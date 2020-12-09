package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
