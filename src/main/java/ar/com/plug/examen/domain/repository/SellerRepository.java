package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.app.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    public abstract SellerEntity findBySellerId(Long sellerId);

    public abstract SellerEntity findByDocumentNumber(Integer document);
}
