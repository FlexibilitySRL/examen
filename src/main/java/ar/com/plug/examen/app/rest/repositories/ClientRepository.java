package ar.com.plug.examen.app.rest.repositories;

import ar.com.plug.examen.app.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
}
