package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ProductoDAO;
import ar.com.plug.examen.domain.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);


    @PostMapping
    public ResponseEntity<ProductoDAO> agregarProducto(@RequestBody ProductoDAO producto) {
        ProductoDAO prod = productoService.agregarProducto(producto);
        logger.info("El producto ID {} fue creado satisfactoriamente", prod.getIdProducto());
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @PostMapping(value = "/borrarproducto/{id}")
    public ResponseEntity<ProductoDAO> borrarProducto(@PathVariable(value = "id") Long id) {
        ProductoDAO producto = null;
        try {
            producto = productoService.borrarProducto(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar borrar el producto ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El producto ID {} fue borrado satisfactoriamente", producto.getIdProducto());
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping(value = "/modificar")
    public ResponseEntity<ProductoDAO> modificarProducto(@RequestBody ProductoDAO producto) {
        ProductoDAO newProducto = null;
        try {
            newProducto = productoService.modificarProducto(producto);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar modificado el producto ID {}: {}", producto.getIdProducto(), e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El producto ID {} fue modificado satisfactoriamente", producto.getIdProducto());
        return new ResponseEntity<>(newProducto, HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<ProductoDAO>> listarProductos() {
        List<ProductoDAO> listaProductos = productoService.listarProductos();
        logger.info("La totalidad de los productos fueron listados satisfactoriamente");
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDAO>> listarProductosActivos() {
        List<ProductoDAO> listaProductos = productoService.listarProductosActivos();
        logger.info("Los productos activos fueron listados satisfactoriamente");
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductoDAO> buscarProducto(@PathVariable(value = "id") Long id) {
        ProductoDAO producto = null;
        try {
            producto = productoService.buscarProducto(id);
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar buscar el producto ID {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("El producto ID {} fue encontrado satisfactoriamente", id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
    

}
