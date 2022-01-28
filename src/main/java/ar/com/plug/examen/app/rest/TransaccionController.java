package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TransaccionApi;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.ProcessClienteService;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import ar.com.plug.examen.domain.service.ProcessProductoService;
import ar.com.plug.examen.domain.service.ProcessTransaccionService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/transacciones")
public class TransaccionController {

    private final ProcessTransaccionService processTransaccionService;
    private final ProcessClienteService processClienteService;
    private final ProcessProductoService processProductoService;
    private final ProcessMessageService processMessage;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    public TransaccionController(ProcessTransaccionService processTransaccionService,
                                 ProcessClienteService processClienteService,
                                 ProcessProductoService processProductoService,
                                 ProcessMessageService processMessage) {
        this.processTransaccionService = processTransaccionService;
        this.processClienteService = processClienteService;
        this.processProductoService = processProductoService;
        this.processMessage = processMessage;
        this.gson = new Gson();
    }

    /*
     *
     * Al crear una transacción se debe tener en cuenta el cliente que realiza la transacción, ademas de la lista de productos que incluyen la compra
     * ademas de el precio total de los productos, y el estado se queda en null hasta ser aprobado en otro path
     *
     * */
    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postTransaccion(@RequestBody TransaccionApi transaccionApi) {
        try {
            Transaccion transaccion = new Transaccion();

            Cliente cliente = processClienteService.findById(transaccionApi.getCliente_id()).orElse(null);
            if (cliente == null)
                return new ResponseEntity<>(processMessage.processMessage("Cliente no existe"), HttpStatus.BAD_REQUEST);
            transaccion.setCliente(cliente);

            double totalPrecio = 0.0;
            List<Producto> productos = new ArrayList<>();
            for (Long p : transaccionApi.getProductos()) {
                Producto producto = processProductoService.findById(p).orElse(null);
                if (producto == null)
                    return new ResponseEntity<>(processMessage.processMessage("Producto " + p + " no existe"), HttpStatus.BAD_REQUEST);
                totalPrecio += producto.getPrecio();
                productos.add(producto);
            }
            transaccion.setPrecioTotal(totalPrecio);
            transaccion.setProductos(productos);

            Transaccion transaccionResponse = processTransaccionService.save(transaccion);
            logger.info("Transaccion creada correctamente");

            return new ResponseEntity<>(gson.toJson(transaccionResponse), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Error al crear transaccion");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTransacciones() {
        try {
            Iterable<Transaccion> transaccions = processTransaccionService.findAll();
            logger.info("Transacciones obtenidas correctamente");
            return new ResponseEntity<>(gson.toJson(transaccions), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener transacciones");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTransaccion(@PathVariable("id") Long id) {
        try {
            Optional<Transaccion> Transaccion = processTransaccionService.findById(id);
            logger.info("Transaccion obtenida correctamente");
            return new ResponseEntity<>(gson.toJson(Transaccion), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener transaccion");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> patchTransaccionStatus(@PathVariable("id") Long id,
                                                    @PathVariable("status") String status) {
        try {
            Optional<Transaccion> Transaccion = processTransaccionService.findById(id);
            Transaccion transaccion = Transaccion.orElse(null);
            if (transaccion == null)
                return new ResponseEntity<>(processMessage.processMessage("Transaccion " + id + " no existe"), HttpStatus.BAD_REQUEST);
            transaccion.setStatus(status);
            Transaccion transaccionResponse = processTransaccionService.save(transaccion);
            logger.info("Transaccion actualizada correctamente");
            return new ResponseEntity<>(gson.toJson(transaccionResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al actualizar transaccion");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
