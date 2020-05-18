package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Seller}
 */
@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
}
