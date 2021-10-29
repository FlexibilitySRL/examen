package ar.com.plug.examen.domain.model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.entities.Transaction;

@Repository
public interface TransactionJpaDao extends JpaRepository<Transaction,Long>{

}
