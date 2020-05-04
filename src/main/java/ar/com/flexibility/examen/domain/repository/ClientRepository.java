package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 *  Client repository to manage CRUD operations for the Client POJO.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

}
