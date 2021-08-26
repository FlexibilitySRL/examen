package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
