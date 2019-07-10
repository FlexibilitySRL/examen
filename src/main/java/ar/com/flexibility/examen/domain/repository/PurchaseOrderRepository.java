package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT true FROM purchase_orders po WHERE po.id = :id")
    boolean existsById(@Param("id") Long id);

}
