package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProcessProductoService extends CrudRepository<Producto, Long> {
}
