package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Transaccion;

public interface TransaccionDao extends JpaRepository<Transaccion, Integer>{

}
