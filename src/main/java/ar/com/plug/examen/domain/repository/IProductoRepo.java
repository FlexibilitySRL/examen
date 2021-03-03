package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.EstadoLogico;
import ar.com.plug.examen.domain.model.ProductoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepo extends JpaRepository<ProductoDAO, Long> {
    List<ProductoDAO> findByEstadoLogico(EstadoLogico activo);
}
