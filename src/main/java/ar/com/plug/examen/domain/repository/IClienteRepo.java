package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.ClienteDAO;
import ar.com.plug.examen.domain.model.EstadoLogico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepo extends JpaRepository<ClienteDAO, Long> {

    ClienteDAO findByDni(String dni);
    List<ClienteDAO> findByEstadoLogico(EstadoLogico activo);
}
