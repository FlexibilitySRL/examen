package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Vendedor;

public interface VendedorDao  extends JpaRepository<Vendedor, Integer> {

}
