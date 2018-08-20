package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Represents the CRUD repository for {@link Seller}.
 */
public interface SellerRepository extends CrudRepository<Seller, Long> {

    /**
     * Lists all clients by delete status.
     * @param deleted flag that indicates if a {@link Seller} is deleted
     * @return all clients by delete status.
     */
    List<Seller> findByDeleted(boolean deleted);
}
