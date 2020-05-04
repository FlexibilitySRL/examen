package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.repository.CrudRepository;

/**
 *  Seller repository to manage CRUD operations for the Seller POJO.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface SellerRepository extends CrudRepository<Seller, Long> {

}
