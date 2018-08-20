package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Represents the CRUD repository for {@link Client}.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    /**
     * Lists all clients by delete status.
     * @param deleted flag that indicates if a {@link Client} is deleted
     * @return all clients by delete status.
     */
    List<Client> findByDeleted(boolean deleted);
}
