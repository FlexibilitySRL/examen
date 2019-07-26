package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
