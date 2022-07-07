package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface ProcessVendedorService extends CrudRepository<Vendedor, Long> {
}
