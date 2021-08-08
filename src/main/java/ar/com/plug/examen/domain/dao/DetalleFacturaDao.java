package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.DetalleFactura;

public interface DetalleFacturaDao extends JpaRepository<DetalleFactura, Integer> {

}
