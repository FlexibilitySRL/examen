package ar.com.plug.examen.domain.repository;

import java.util.List;

import ar.com.plug.examen.domain.model.PurchaseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long>
{
	Page<PurchaseDetail> findAllByPurchaseId(Long purchaseId, Pageable pageable);
	Page<PurchaseDetail> findAllByProductId(Long productId, Pageable pageable);

	List<PurchaseDetail> findAllByPurchaseId(Long purchaseId);
	List<PurchaseDetail> findAllByProductId(Long productId);

	boolean existsByPurchaseId(Long purchaseId);
}
