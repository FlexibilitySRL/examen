package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.dto.responses.ListaComprasResponse;
import ar.com.plug.examen.dto.responses.TransaccionResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface TransaccionService {

    ResponseEntity startTransaccion(Long clienteId);
    List<Transaccion> listAllTransacciones();
    TransaccionResponse endTransaccion(Long transaccionId);
    Boolean transaccionActivo(Long transaccionId);
    ListaComprasResponse findAllByTransaccion(Long transaccionId);
    ResponseEntity saveTransaccion(TransaccionResponse transaccionResponse, Transaccion transaccion);
}
