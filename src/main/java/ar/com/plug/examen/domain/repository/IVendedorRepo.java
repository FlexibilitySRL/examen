package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.EstadoLogico;
import ar.com.plug.examen.domain.model.VendedorDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVendedorRepo extends JpaRepository<VendedorDAO, Long> {
    List<VendedorDAO> findByEstadoLogico(EstadoLogico activo);
}
