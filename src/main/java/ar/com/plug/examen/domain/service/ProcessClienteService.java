package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ProcessClienteService extends CrudRepository<Cliente, Long> {
}
