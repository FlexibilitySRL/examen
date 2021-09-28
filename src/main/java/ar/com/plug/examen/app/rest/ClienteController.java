package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.dto.requests.ClienteRequest;
import ar.com.plug.examen.dto.responses.ClienteResponse;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity addCliente(@RequestBody ClienteRequest clienteRequest){
        return ResponseEntity.ok(clienteService.addCliente(clienteRequest));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.deleteCliente(id));
    }

    @PostMapping(path = "/update", produces = "application/json")
    public ResponseEntity updateCliente(@RequestBody ClienteRequest clienteRequest, @RequestParam Long id){
        return ResponseEntity.ok(clienteService.updateCliente(clienteRequest, id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteResponse>> listClientes() throws Exception {
        List<ClienteResponse> clientes = clienteService.listClientes();
      if (Objects.nonNull(clientes) && !clientes.isEmpty()) {
        return ResponseEntity.ok(clientes);
      }
      else {
          throw new RuntimeException("No hay clientes");
      }
    }



}
