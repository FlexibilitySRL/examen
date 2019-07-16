package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
