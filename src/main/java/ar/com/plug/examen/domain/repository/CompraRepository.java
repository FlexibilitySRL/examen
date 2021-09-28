package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Compra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    public List<Compra> findAllByTransaccionId(Long transaccionId);
    @Query(value = "SELECT * FROM WHERE transaccionId = :transaccionId AND producto_id = :productoId", nativeQuery = true)
    public List<Compra> findByProductoIdAndTransaccion(Long productoId, Long transaccionId);
}
