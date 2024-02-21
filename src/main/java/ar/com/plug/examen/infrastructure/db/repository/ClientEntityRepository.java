package ar.com.plug.examen.infrastructure.db.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.ClientEntity;

public interface ClientEntityRepository extends CrudRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByEmail(String email);

}
