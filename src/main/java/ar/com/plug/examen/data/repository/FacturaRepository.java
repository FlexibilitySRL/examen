package ar.com.plug.examen.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.data.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Long> {
	Optional<Factura> findFacturaByCodigo(String codigo);
}
