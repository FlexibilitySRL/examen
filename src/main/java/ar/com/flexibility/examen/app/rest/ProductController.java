package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="Servcio de administracion de Productos")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;


    // curl -X GET -H 'Content-Type: application/json' -H 'Accept: application/json'
    // http://localhost:8080/products/all
    @ApiOperation(value = "Obtiene todos los productos")
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

    // curl -X GET -H 'Content-Type: application/json' -H 'Accept: application/json'
    // http://localhost:8080/products/{id}
    @ApiOperation(value = "Obtiene un Producto por ID")
    @GetMapping(path = "{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {

        Product product = productService.findOne(id);

        if (product == null) {

            MessageApi messageApi = new MessageApi();
            messageApi.setMessage(String.format("No existe el producto con ID=%d.", id));
            log.info(messageApi.getMessage());
            return new ResponseEntity<>(messageApi, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductApi>(new ProductApi(product), HttpStatus.OK);
    }


    // curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json'
    // http://localhost:8080/products/add
    // -d '{"description":"nuevo producto 8", "price":88.00}'
    @ApiOperation(value = "Crea un producto")
    @PostMapping(path = "add")
    public ResponseEntity<?> add(
            @ApiParam(value = "Producto a crear") @RequestBody ProductApi productApi) {

        Product product = productService.add(new Product(productApi));
        log.info("Product created.");
        return new ResponseEntity<>(new ProductApi(product), HttpStatus.CREATED);
    }


    // curl -X PUT -H 'Content-Type: application/json' -H 'Accept: application/json'
    // http://localhost:8080/products/update
    // -d '{"id":8, "description":"nuevo producto 8 updated", "price":80.00}'
    @ApiOperation(value = "Actualiza un producto")
    @PutMapping(path = "update")
    public ResponseEntity<?> update(
            @ApiParam(value = "Producto a actualizar") @RequestBody ProductApi productApi) {

        Product productToUse = new Product(productApi);
        Product productToUpdate = productService.findOne(productToUse.getId());

        if (productToUpdate==null) {

            log.info(String.format("No existe el producto con ID=%d.", productToUse.getId()));
            return new ResponseEntity<>(productApi, HttpStatus.NOT_FOUND);

        } else if (productToUse.equals(productToUpdate)){

            log.info("No hay cambios a actualizar en el Producto. ");
            return new ResponseEntity<>(productApi, HttpStatus.OK);

        } else {
            // Aplicar cambios
            Product productUpdated = productService.update(productToUse);
            log.info("Producto actualizado.");
            return new ResponseEntity<>(new ProductApi(productUpdated), HttpStatus.OK);
        }

    }


    // curl -X DELETE http://localhost:8080/products/delete/{id}
    @ApiOperation(value = "Elimina un producto")
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> delete(
            @ApiParam(value = "Producto a eliminar") @PathVariable Long id) {

        MessageApi messageApi = new MessageApi();

        Boolean exist = productService.exists(id);

        if (!exist) {
            messageApi.setMessage(String.format("No existe el producto con ID=%d.", id));
            log.info(messageApi.getMessage());
            return new ResponseEntity<>(messageApi, HttpStatus.NOT_FOUND);

        } else {

            Boolean deleted = productService.delete(id);

            if (deleted) {
                messageApi.setMessage("El Producto fue Eliminado.");
                log.info(messageApi.getMessage());
                return new ResponseEntity<>(messageApi, HttpStatus.OK);

            } else {
                messageApi.setMessage("El Producto no pudo ser Eliminado.");
                log.info(messageApi.getMessage());
                return new ResponseEntity<>(messageApi, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
