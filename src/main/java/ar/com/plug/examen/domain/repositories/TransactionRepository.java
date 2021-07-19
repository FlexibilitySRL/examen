package ar.com.plug.examen.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "Update transactions"
			+ "  SET status = :status"
			+ "  where id = :id"	
			,nativeQuery = true)
	public void rejectStatus(Long id, Integer status);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "Update transactions"
			+ "  SET status = :status "
			+ "  where id = :id"	
			,nativeQuery = true)
	public void approveStatus(Long id, Integer status);
	
}
