package ar.com.plug.examen.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.dao.entities.Transaction;

@Repository
public interface TransactionJpaDao extends JpaRepository<Transaction,Long>{

}
