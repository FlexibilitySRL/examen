package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long> {
}
