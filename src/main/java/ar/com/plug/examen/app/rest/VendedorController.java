package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.exception.VendedorNoEncontradoException;
import ar.com.plug.examen.domain.model.TransaccionDAO;
import ar.com.plug.examen.domain.model.VendedorDAO;
import ar.com.plug.examen.domain.service.IVendedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    private static final Logger logger = LoggerFactory.getLogger(VendedorController.class);


    @PostMapping
    public ResponseEntity<VendedorDAO> agregarVendedor(@RequestBody VendedorDAO vendedor) {
        VendedorDAO vend = vendedorService.agregarVendedor(vendedor);
        logger.info("El vendedor ID {} fue creado satisfactoriamente", vend.getIdVendedor());
        return new ResponseEntity<>(vend, HttpStatus.CREATED);
    }

    @PostMapping(value = "/borrarvendedor/{id}")
    public ResponseEntity<VendedorDAO> borrarVendedor(@PathVariable(value = "id") Long id) {
        VendedorDAO vendedor = null;
        try {
            vendedor = vendedorService.borrarVendedor(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar borrar el vendedor ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El vendedor ID {} fue borrado satisfactoriamente", vendedor.getIdVendedor());
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    @PostMapping(value = "/modificar")
    public ResponseEntity<VendedorDAO> modificarVendedor(@RequestBody VendedorDAO vendedor) {
        VendedorDAO vend = null;
        try {
            vend = vendedorService.modificarVendedor(vendedor);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar modificar el vendedor ID {}: {}", vendedor.getIdVendedor(), e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El vendedor ID {} fue modificado satisfactoriamente", vend.getIdVendedor());
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<VendedorDAO>> listarVendedor() {
        List<VendedorDAO> lisaVendedores = vendedorService.listarVendedores();
        logger.info("La totalidad de los vendedores fueron listados satisfactoriamente");
        return new ResponseEntity<>(lisaVendedores, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VendedorDAO>> listarVendedorActivos() {
        List<VendedorDAO> lisaVendedores =  vendedorService.listarVendedoresActivos();
        logger.info("Los vendedores activos fueron listados satisfactoriamente");
        return new ResponseEntity<>(lisaVendedores, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorDAO> buscarVendedor(@PathVariable(value = "id") Long id) throws VendedorNoEncontradoException {
        VendedorDAO vendedor = null;
        try {
            vendedor = vendedorService.buscarVendedor(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar buscar el vendedor ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El vendeor ID {} fue encontrado satisfactoriamente", id);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    @GetMapping(value = "/transacciones/{idVendedor}")
    public ResponseEntity<List<TransaccionDAO>> buscarTransaccionesPorVendedor(@PathVariable(value = "idVendedor") Long idVendedor) {
        List<TransaccionDAO> listaTransacciones = new ArrayList<>();
        try {
            listaTransacciones = vendedorService.buscarTransaccionesPorVendedor(idVendedor);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar listar las transacciones del vendedor ID {}: {}", idVendedor, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Las transacciones del vendedor ID {} fueron listadas satisfactoriamente", idVendedor);
        return new ResponseEntity<>(listaTransacciones, HttpStatus.OK);
    }

    @PostMapping(value = "/aprobar/{idTransaccion}/{idVendedor}")
    public ResponseEntity<TransaccionDAO> aprobarTransaccion(@PathVariable(value = "idTransaccion") Long idTransaccion,
                                                             @PathVariable(value = "idVendedor") Long idVendedor) {
        TransaccionDAO transaccion = null;
        try {
            transaccion = vendedorService.aprobarTransaccion(idTransaccion, idVendedor);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar aprobar la transaccion ID {} por vendedor ID {}: {}", idTransaccion, idVendedor, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("La transaccion ID {} fue aprobada satisfactorimente por el vendedor ID {}", idTransaccion, idVendedor);
        return new ResponseEntity<>(transaccion, HttpStatus.OK);
    }

    @PostMapping(value = "/rechazar/{idTransaccion}/{idVendedor}")
    public ResponseEntity<TransaccionDAO> rechazarTransaccion(@PathVariable(value = "idTransaccion") Long idTransaccion,
                                                              @PathVariable(value = "idVendedor") Long idVendedor) {
        TransaccionDAO transaccion = null;
        try {
            transaccion = vendedorService.rechazarTransaccion(idTransaccion, idVendedor);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar rechazar la transaccion ID {} por vendedor ID {}: {}", idTransaccion, idVendedor, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("La transaccion ID {} fue rechazada satisfactorimente por el vendedor ID {}", idTransaccion, idVendedor);
        return new ResponseEntity<>(transaccion, HttpStatus.OK);
    }

}
