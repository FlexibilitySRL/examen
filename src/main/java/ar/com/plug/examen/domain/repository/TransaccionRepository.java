package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ar.com.plug.examen.domain.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>, JpaSpecificationExecutor<Transaccion> {

    
}
