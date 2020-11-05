package ar.com.plug.examen.dao;

import ar.com.plug.examen.model.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersDAO extends JpaRepository<Sellers, Long> {
}
