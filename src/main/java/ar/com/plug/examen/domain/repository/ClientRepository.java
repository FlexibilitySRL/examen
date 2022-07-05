package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.app.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public abstract ClientEntity findByDocumentNumber(int documentNumber);
}
