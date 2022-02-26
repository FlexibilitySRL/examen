package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
	Page<Purchase> findAllByApprovedTrue(Pageable pageable);
	Purchase findByReceiptNumber(String receiptNumber);
	Page<Purchase> findAllByClientId(Long clientId, Pageable pageable);
}
