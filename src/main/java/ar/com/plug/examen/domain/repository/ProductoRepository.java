package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
