package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository  extends JpaRepository<Vendedor, Long> {
}
