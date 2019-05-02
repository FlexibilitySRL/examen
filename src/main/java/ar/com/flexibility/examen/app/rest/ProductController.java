package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
@Api("Servcio de administracion de Productos")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;


    @ApiOperation(value = "Obtiene todos los productos",
            response = ProductApi.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Obtención de Lista de Productos exitosa.")
    @GetMapping(path = "all")
    public ResponseEntity<?> findAll() {

        List<Product> products = productService.findAll();
        List<ProductApi> productsApi = new ArrayList<>();

        products.forEach(product -> productsApi.add(new ProductApi(product)));

        if (CollectionUtils.isEmpty(productsApi)){
            log.info("Products Empty.");
        } else {
            log.info(String.format("%d Products Found.", productsApi.size()));
        }

        return new ResponseEntity<List>(productsApi, HttpStatus.OK);
    }


    @ApiOperation(value = "Obtiene un Producto", response = ProductApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Obtención del Producto éxitosa"),
            @ApiResponse(code = 404, message = "No se encontró el Producto")
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(
            @ApiParam(value = "ID del producto a obtener", required = true)
            @PathVariable Long id) {

        Product product = productService.findOne(id);

        if (product == null) {

            MessageApi messageApi = new MessageApi();
            messageApi.setMessage(String.format("No existe el producto con ID=%d.", id));
            log.info(messageApi.getMessage());
            return new ResponseEntity<>(messageApi, HttpStatus.NOT_FOUND);
        }

        log.info("Se encontró el Producto con Éxito.");
        return new ResponseEntity<ProductApi>(new ProductApi(product), HttpStatus.OK);
    }


    @ApiOperation(value = "Crea un producto", response = ProductApi.class)
    @ApiResponse(code = 201, message = "Producto creado con éxito")
    @PostMapping(path = "add")
    public ResponseEntity<?> add(
            @ApiParam(value = "Producto a crear", required = true)
            @RequestBody ProductApi productApi) {

        Product product = productService.add(new Product(productApi));
        log.info("Producto Creado con Éxito.");
        return new ResponseEntity<>(new ProductApi(product), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Actualiza un producto", response = ProductApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto actualizado con éxito"),
            @ApiResponse(code = 412, message = "No hay cambios a actualizar"),
            @ApiResponse(code = 404, message = "No se encontró el Producto")
    })
    @PutMapping(path = "update")
    public ResponseEntity<?> update(
            @ApiParam(value = "Producto a actualizar", required = true)
            @RequestBody ProductApi productApi) {

        Product productToUse = new Product(productApi);
        Product productToUpdate = productService.findOne(productToUse.getId());

        if (productToUpdate==null) {

            log.info(String.format("No existe el producto con ID=%d.", productToUse.getId()));
            return new ResponseEntity<>(productApi, HttpStatus.NOT_FOUND);

        } else if (productToUse.equals(productToUpdate)){

            log.info("No hay cambios a actualizar en el Producto. ");
            return new ResponseEntity<>(productApi, HttpStatus.PRECONDITION_FAILED);

        } else {
            // Aplicar cambios
            Product productUpdated = productService.update(productToUse);
            log.info("Producto actualizado con Éxito.");
            return new ResponseEntity<>(new ProductApi(productUpdated), HttpStatus.OK);
        }

    }


    @ApiOperation(value = "Elimina un producto", response = MessageApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto eliminado con éxito"),
            @ApiResponse(code = 500, message = "El Producto no pudo ser eliminado"),
            @ApiResponse(code = 404, message = "No se encontró el Producto")
    })
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> delete(
            @ApiParam(value = "ID del Producto a eliminar", required = true)
            @PathVariable Long id) {

        MessageApi messageApi = new MessageApi();

        Boolean exist = productService.exists(id);

        if (!exist) {
            messageApi.setMessage(String.format("No existe el producto con ID=%d.", id));
            log.info(messageApi.getMessage());
            return new ResponseEntity<>(messageApi, HttpStatus.NOT_FOUND);

        } else {

            Boolean deleted = productService.delete(id);

            if (!deleted) {

                messageApi.setMessage("El Producto no pudo ser Eliminado.");
                log.info(messageApi.getMessage());
                return new ResponseEntity<>(messageApi, HttpStatus.INTERNAL_SERVER_ERROR);

            } else {

                messageApi.setMessage("El Producto fue Eliminado con Éxito.");
                log.info(messageApi.getMessage());
                return new ResponseEntity<>(messageApi, HttpStatus.OK);
            }
        }

    }

}
