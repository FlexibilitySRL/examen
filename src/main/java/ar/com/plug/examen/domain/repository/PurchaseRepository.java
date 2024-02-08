package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Purchase entities.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}
