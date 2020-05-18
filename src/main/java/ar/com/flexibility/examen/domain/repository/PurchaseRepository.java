package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	Purchase findById(Long id);
	
}
