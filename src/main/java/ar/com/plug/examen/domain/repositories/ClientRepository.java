package ar.com.plug.examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

}
