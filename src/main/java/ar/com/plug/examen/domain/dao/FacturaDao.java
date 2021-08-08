package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Factura;

public interface FacturaDao extends JpaRepository<Factura, Integer>{

}
