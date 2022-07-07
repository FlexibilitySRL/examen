package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductoApi;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import ar.com.plug.examen.domain.service.ProcessProductoService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/* Los productos solamente tiene nombre y precio */
@RestController
@RequestMapping(path = "/productos")
public class ProductoController {

    private final ProcessProductoService processProductoService;
    private final ProcessMessageService processMessage;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    public ProductoController(ProcessProductoService processProductoService, ProcessMessageService processMessage) {
        this.processProductoService = processProductoService;
        this.processMessage = processMessage;
        this.gson = new Gson();
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postProducto(@RequestBody ProductoApi productoApi) {
        try {
            Producto producto = new Producto();
            producto.setNombre(productoApi.getNombre());
            producto.setPrecio(productoApi.getPrecio());
            Producto productoResponse = processProductoService.save(producto);
            logger.info("Producto guardado correctamente");
            return new ResponseEntity<>(gson.toJson(productoResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al guardar producto");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductos() {
        try {
            Iterable<Producto> productoList = processProductoService.findAll();
            logger.info("Productos obtenidos correctamente");
            return new ResponseEntity<>(gson.toJson(productoList), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener productos");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProducto(@PathVariable("id") Long id) {
        try {
            Optional<Producto> producto = processProductoService.findById(id);
            logger.info("Producto obtenido correctamente");
            return new ResponseEntity<>(gson.toJson(producto), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al obtener producto");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchProducto(@PathVariable("id") Long id, @RequestBody ProductoApi productoApi) {
        try {
            Optional<Producto> optionalProducto = processProductoService.findById(id);
            Producto producto = optionalProducto.orElse(null);
            if (producto == null)
                return new ResponseEntity<>(processMessage.processMessage("Producto no existe"), HttpStatus.OK);
            producto.setNombre(productoApi.getNombre());
            producto.setPrecio(productoApi.getPrecio());
            Producto productoResponse = processProductoService.save(producto);
            logger.info("Productos actualizado correctamente");
            return new ResponseEntity<>(gson.toJson(productoResponse), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al actualizar producto");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteProducto(@PathVariable("id") Long id) {
        try {
            processProductoService.deleteById(id);
            logger.info("Productos eliminado correctamente");
            return new ResponseEntity<>(processMessage.processMessage("Producto Eliminado"), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error al eliminar producto");
            return new ResponseEntity<>(processMessage.processMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
