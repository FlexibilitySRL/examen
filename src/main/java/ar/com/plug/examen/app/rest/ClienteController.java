package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClienteApi;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ProcessClienteService;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/* Datos del cliente nombre y apellido */
@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private final ProcessClienteService processClienteService;
    private final ProcessMessageService processMessage;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    public ClienteController(ProcessClienteService processClienteService, ProcessMessageService processMessage) {
        this.processClienteService = processClienteService;
        this.processMessage = processMessage;
        this.gson = new Gson();
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCliente(@RequestBody ClienteApi ClienteApi) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(ClienteApi.getNombre());
            cliente.setApellido(ClienteApi.getApellido());
            Cliente personaResponse = processClienteService.save(cliente);
            logger.info("Cliente guardado correctamente");
            return new ResponseEntity<>(gson.toJson(personaResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al guardar cliente");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getClientes() {
        try {
            Iterable<Cliente> ClienteList = processClienteService.findAll();
            logger.info("Clientes obtenidos correctamente");
            return new ResponseEntity<>(gson.toJson(ClienteList), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener clientes");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCliente(@PathVariable("id") Long id) {
        try {
            Optional<Cliente> Cliente = processClienteService.findById(id);
            logger.info("Cliente obtenido correctamente");
            return new ResponseEntity<>(gson.toJson(Cliente), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener cliente");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putCliente(@PathVariable("id") Long id, @RequestBody ClienteApi ClienteApi) {
        try {
            Optional<Cliente> optionalCliente = processClienteService.findById(id);
            Cliente cliente = optionalCliente.orElse(null);
            if (cliente == null)
                return new ResponseEntity<>(processMessage.processMessage("Cliente no existe"), HttpStatus.BAD_REQUEST);
            cliente.setNombre(ClienteApi.getNombre());
            cliente.setApellido(ClienteApi.getApellido());
            Cliente personaResponse = processClienteService.save(cliente);
            logger.info("Cliente actualizado correctamente");
            return new ResponseEntity<>(gson.toJson(personaResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al actualizar cliente");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCliente(@PathVariable("id") Long id) {
        try {
            processClienteService.deleteById(id);
            logger.info("Cliente eliminado correctamente");
            return new ResponseEntity<>(processMessage.processMessage("Cliente Eliminado"), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al eliminar cliente");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
