package ar.com.plug.examen.domain.model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.entities.Client;

@Repository
public interface ClientJpaDao extends JpaRepository<Client,Long>{

}
