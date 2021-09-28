package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

}
