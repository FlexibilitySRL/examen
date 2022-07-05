package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.app.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    public abstract List<PurchaseEntity> findByReceiptId(Long receiptId);
}
