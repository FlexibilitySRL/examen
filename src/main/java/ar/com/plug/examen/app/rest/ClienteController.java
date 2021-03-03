package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ClienteDAO;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);


    @PostMapping
    public ResponseEntity<ClienteDAO> agregarCliente(@RequestBody ClienteDAO cliente) {
        ClienteDAO cli = clienteService.agregarCliente(cliente);
        logger.info("El cliente ID {} fue creado satisfactoriamente", cli.getIdCliente());
        return new ResponseEntity<>(cli, HttpStatus.CREATED);
    }

    @PostMapping(value = "/borrarcliente/{id}")
    public ResponseEntity<ClienteDAO> borrarCliente(@PathVariable(value = "id") Long id) {
        ClienteDAO cliente = null;
        try {
            cliente = clienteService.borrarCliente(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar borrar el cliente ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El cliente ID {} fue borrado satisfactoriamente", cliente.getIdCliente());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "/modificar")
    public ResponseEntity<ClienteDAO> modificarCliente(@RequestBody ClienteDAO cliente) {
        ClienteDAO cli = null;
        try {
            cli = clienteService.modificarCliente(cliente);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar modificar el cliente ID {}: {}", cliente.getIdCliente(), e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El cliente ID {} fue modificado satisfactoriamente", cliente.getIdCliente());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<ClienteDAO>> listarClientes() {
        List<ClienteDAO> listaClientes = clienteService.listarClientes();
        logger.info("La totalidad de los clientes fueron listados satisfactoriamente");
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDAO>> listarClientesActivos() {
        List<ClienteDAO> listaClientes = clienteService.listarClientesActivos();
        logger.info("Los clientes activos fueron listados satisfactoriamente");
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDAO> buscarCliente(@PathVariable(value = "id") Long id) {
        ClienteDAO cliente = null;
        try {
            cliente = clienteService.buscarCliente(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar buscar el cliente ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El cliente ID {} fue encontrado satisfactoriamente", id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping(value = "/transacciones/{idCiente}")
    public ResponseEntity<List<TransaccionDAO>> buscarTransaccionesPorCliente(@PathVariable(value = "idCiente") Long idCiente) {
        List<TransaccionDAO> listaTransacciones = new ArrayList<>();
        try {
            listaTransacciones = clienteService.buscarTransaccionesPorCliente(idCiente);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar listar las transacciones del cliente ID {}: {}", idCiente, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Las transacciones del cliente ID {} fueron listadas satisfactoriamente", idCiente);
        return new ResponseEntity<>(listaTransacciones, HttpStatus.OK);
    }


}
