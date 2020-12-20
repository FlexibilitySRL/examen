package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Operation;

public interface OrderRepository extends JpaRepository<Operation, Long>{

}
