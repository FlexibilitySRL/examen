package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.dto.requests.CompraRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CompraService {
    ResponseEntity listCompras();
    ResponseEntity addCompra(CompraRequest compraRequest);
    ResponseEntity updateCompra(CompraRequest compraRequest);
    ResponseEntity deleteCompraTransaccion(Long productoId, Long transaccionId);
    List<Compra> listComprasByTransaccion(Long idTransaccion);
}
