package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
