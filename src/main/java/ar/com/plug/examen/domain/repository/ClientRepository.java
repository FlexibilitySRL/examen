package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

}
