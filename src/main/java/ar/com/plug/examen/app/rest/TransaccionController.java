package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ProductoDAO;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.service.ITransaccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transaccion")
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    private static final Logger logger = LoggerFactory.getLogger(TransaccionController.class);


    @PostMapping
    public ResponseEntity<TransaccionDAO> agregarTransaccion(@RequestBody TransaccionDAO transaccion) {
        TransaccionDAO tx = null;
        try {
            tx = transaccionService.agregarTransaccion(transaccion);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar crear una transaccion: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("La transaccion ID {} fue creada satisfactoriamente", tx.getIdTransaccion());
        return new ResponseEntity<>(tx, HttpStatus.CREATED);
    }

    @PostMapping(value = "/modificar")
    public ResponseEntity<TransaccionDAO> modificarTransaccion(@RequestBody TransaccionDAO transaccion) {
        TransaccionDAO tx = null;
        try {
            tx = transaccionService.modificarTransaccion(transaccion);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar modificar la transaccion ID {}: {}", transaccion.getIdTransaccion(), e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("La transaccion ID {} fue modificada satisfactoriamente", tx.getIdTransaccion());
        return new ResponseEntity<>(tx, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TransaccionDAO>> listarTransacciones() {
        List<TransaccionDAO> listaTransacciones = transaccionService.listarTransacciones();
        logger.info("Las transacciones fueron listadas satisfactoriamente");
        return new ResponseEntity<>(listaTransacciones, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransaccionDAO> buscarTransaccion(@PathVariable(value = "id") Long id) {
        TransaccionDAO tx = null;
        try {
            tx = transaccionService.buscarTransaccion(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar buscar la transaccion ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("La transaccion ID {} fue encontrada satisfactoriamente", id);
        return new ResponseEntity<>(tx, HttpStatus.OK);
    }

    @PostMapping(value = "/agregar/{id}")
    public ResponseEntity<TransaccionDAO> agregarProductos(@PathVariable(value = "id") Long idTransaccion,
                                                           @RequestBody List<ProductoDAO> listaProductos) {
        TransaccionDAO tx = null;
        try {
            tx = transaccionService.agregarProductos(idTransaccion, listaProductos);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar agregar productos a la transaccion ID {}: {}", idTransaccion, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Los productos fueron agregados satisfactoriamente a transaccion ID {}", idTransaccion);
        return new ResponseEntity<>(tx, HttpStatus.OK);
    }

    @PostMapping(value = "/remover/{id}")
    public ResponseEntity<TransaccionDAO> removerProductos(@PathVariable(value = "id") Long idTransaccion,
                                                           @RequestBody List<ProductoDAO> listaProductos) {
        TransaccionDAO tx = null;
        try {
            tx = transaccionService.removerProductos(idTransaccion, listaProductos);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar remover productos a la transaccion ID {}: {}", idTransaccion, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Los productos fueron removidos satisfactoriamente a transaccion ID {}", idTransaccion);
        return new ResponseEntity<>(tx, HttpStatus.OK);
    }


}
