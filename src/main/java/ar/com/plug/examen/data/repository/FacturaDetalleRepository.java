package ar.com.plug.examen.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.data.entity.FacturaDetalle;

@Repository
public interface FacturaDetalleRepository extends CrudRepository<FacturaDetalle,Long> {

}
