package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Represents the CRUD repository for {@link Product}.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Lists all clients by delete status.
     * @param deleted flag that indicates if a {@link Product} is deleted
     * @return all clients by delete status.
     */
    List<Product> findByDeleted(boolean deleted);
}