package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    /**
     * query that find a seller by documentNumber
     * @param documentNumber value to find in sller table
     * @return object of seller if exist in database.
     */
    Seller findSellerBydocumentNumber(String documentNumber);
}
