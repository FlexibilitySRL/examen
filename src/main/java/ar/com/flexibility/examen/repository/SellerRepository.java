package ar.com.flexibility.examen.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Seller entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
