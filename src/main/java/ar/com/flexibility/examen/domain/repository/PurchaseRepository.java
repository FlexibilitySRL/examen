package ar.com.flexibility.examen.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.utils.PurchaseStatus;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	@Query("FROM purchases p WHERE p.id = :id AND p.status = :status ")
	public Optional<Purchase> findByIdAndStatus(@Param("id") Long id, @Param("status") PurchaseStatus status);
}
