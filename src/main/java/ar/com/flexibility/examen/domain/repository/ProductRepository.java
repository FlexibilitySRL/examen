package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *  Product repository to manage CRUD operations for the Product POJO.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
