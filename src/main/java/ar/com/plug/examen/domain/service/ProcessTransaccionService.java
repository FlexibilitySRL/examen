package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Transaccion;
import org.springframework.data.repository.CrudRepository;

public interface ProcessTransaccionService extends CrudRepository<Transaccion, Long> {
}
