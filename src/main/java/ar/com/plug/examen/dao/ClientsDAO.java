package ar.com.plug.examen.dao;

import ar.com.plug.examen.model.Clients;
import ar.com.plug.examen.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsDAO extends JpaRepository<Clients, Long> {

    public Clients findByName(String name);
}
