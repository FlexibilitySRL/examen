package ar.com.flexibility.examen.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Purchase;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
	
	 @Query("FROM purchases p WHERE p.status = :status ")
	 List<Purchase> getByStatus(@Param("status") String status);
}
