package ar.com.plug.examen.domain.repository;


import ar.com.plug.examen.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
   /**
    * query that find a relationship of purchase with product, seller or customer
    * @param id_customer unique value of customer
    * @param idProduct unique value of product
    * @param idSeller unique value of seller
    * @return a list of objet purchase with conditions of match with params
    */
   List<Purchase> findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(Long id_customer , Long idProduct, Long idSeller);

}
