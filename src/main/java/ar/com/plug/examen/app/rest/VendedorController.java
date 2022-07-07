package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClienteApi;
import ar.com.plug.examen.app.api.VendedorApi;
import ar.com.plug.examen.domain.model.Persona;
import ar.com.plug.examen.domain.model.Vendedor;
import ar.com.plug.examen.domain.service.ProcessClienteService;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import ar.com.plug.examen.domain.service.ProcessVendedorService;
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
@RequestMapping(path = "/vendedores")
public class VendedorController {

    private final ProcessVendedorService processVendedorService;
    private final ProcessMessageService processMessage;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    public VendedorController(ProcessVendedorService processClienteService, ProcessMessageService processMessage) {
        this.processVendedorService = processClienteService;
        this.processMessage = processMessage;
        this.gson = new Gson();
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postVendedor(@RequestBody VendedorApi ClienteApi) {
        try {
            Vendedor vendedor = new Vendedor();
            vendedor.setNombre(ClienteApi.getNombre());
            vendedor.setApellido(ClienteApi.getApellido());
            Vendedor personaResponse = processVendedorService.save(vendedor);
            logger.info("Vendedor guardado correctamente");
            return new ResponseEntity<>(gson.toJson(personaResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al guardar vendedor");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getVendedores() {
        try {
            Iterable<Vendedor> ClienteList = processVendedorService.findAll();
            logger.info("Vendedores obtenidos correctamente");
            return new ResponseEntity<>(gson.toJson(ClienteList), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener vendedores");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        try {
            Optional<Vendedor> Cliente = processVendedorService.findById(id);
            logger.info("Vendedor obtenido correctamente");
            return new ResponseEntity<>(gson.toJson(Cliente), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener vendedor");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody VendedorApi ClienteApi) {
        try {
            Optional<Vendedor> optionalCliente = processVendedorService.findById(id);
            Vendedor vendedor = optionalCliente.orElse(null);
            if (vendedor == null)
                return new ResponseEntity<>(processMessage.processMessage("Vendedor no existe"), HttpStatus.BAD_REQUEST);
            vendedor.setNombre(ClienteApi.getNombre());
            vendedor.setApellido(ClienteApi.getApellido());
            Vendedor personaResponse = processVendedorService.save(vendedor);
            logger.info("Vendedor actualizado correctamente");
            return new ResponseEntity<>(gson.toJson(personaResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al actualizar vendedor");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteVendedor(@PathVariable("id") Long id) {
        try {
            processVendedorService.deleteById(id);
            logger.info("Vendedor eliminado correctamente");
            return new ResponseEntity<>(processMessage.processMessage("Vendedor Eliminado"), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al eliminar vendedor");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
