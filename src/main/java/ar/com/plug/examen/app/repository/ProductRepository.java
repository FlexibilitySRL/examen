package ar.com.plug.examen.app.repository;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class to manage the queries to the DB, for now only use the basic operations
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
