package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Cliente;

public interface ClienteDao  extends JpaRepository<Cliente, Integer> {

}
