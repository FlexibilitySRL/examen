package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.EstadoTransaccion;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransaccionRepo extends JpaRepository<TransaccionDAO, Long> {
    List<TransaccionDAO> findByEstadoTransaccion(EstadoTransaccion estado);
}
