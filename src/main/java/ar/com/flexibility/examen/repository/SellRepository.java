package ar.com.flexibility.examen.repository;

import ar.com.flexibility.examen.domain.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Sell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

}
