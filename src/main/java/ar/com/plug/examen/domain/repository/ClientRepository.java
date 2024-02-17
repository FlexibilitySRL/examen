package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
