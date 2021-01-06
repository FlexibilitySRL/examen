package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT s FROM Purchase  s where s.idWorker = :id")
    List<Purchase> findPurchaseByIdWorker(@Param("id") Long id);


}
