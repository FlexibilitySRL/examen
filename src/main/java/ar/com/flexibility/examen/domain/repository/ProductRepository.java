package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Product;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
