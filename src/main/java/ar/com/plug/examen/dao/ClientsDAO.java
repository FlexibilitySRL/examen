package ar.com.plug.examen.dao;

import ar.com.plug.examen.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsDAO extends JpaRepository<Clients, Long> {
}
