package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByNombre(String nombre);
}
