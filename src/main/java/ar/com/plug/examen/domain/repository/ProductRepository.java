package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AGB
 */

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    public ProductModel findByName(String name);

}
