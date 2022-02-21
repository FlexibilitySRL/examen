package ar.com.plug.examen.domain.repository;


import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
   List<Purchase> findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(Long id_customer , Long idProduct, Long idSeller);

}
