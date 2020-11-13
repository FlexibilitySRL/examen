package ar.com.plug.examen.app.repository;

import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class to manage the queries to the DB, for now only use the basic operations
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
