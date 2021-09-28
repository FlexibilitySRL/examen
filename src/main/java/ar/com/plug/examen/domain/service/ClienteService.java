package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.dto.requests.ClienteRequest;
import ar.com.plug.examen.dto.responses.ClienteResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ClienteService {
    ResponseEntity addCliente(ClienteRequest clienteRequest);
    ResponseEntity deleteCliente(Long id);
    ResponseEntity updateCliente(ClienteRequest clienteRequest, Long id);
    List<ClienteResponse> listClientes();
}
