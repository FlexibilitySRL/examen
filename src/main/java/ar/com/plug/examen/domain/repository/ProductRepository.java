package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Product;


/**
 *
 * @author Joan Rey
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
