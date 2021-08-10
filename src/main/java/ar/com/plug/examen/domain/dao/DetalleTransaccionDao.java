package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.DetalleTransaccion;

public interface DetalleTransaccionDao extends JpaRepository<DetalleTransaccion, Integer> {

}
