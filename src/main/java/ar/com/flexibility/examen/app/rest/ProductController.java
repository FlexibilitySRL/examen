package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.GenericProductException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static ar.com.flexibility.examen.domain.exception.GenericProductException.*;
import static ar.com.flexibility.examen.domain.service.ProductService.*;


@RestController
@RequestMapping(path = "/products")
@Api("Servcio de administracion de Productos")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;


    @ApiOperation(value = "Obtiene todos los productos",
            response = ProductApi.class, responseContainer = "List")
    @ApiResponse(code = 200, message = FIND_ALL_OK)
    @GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {

        List<Product> products = productService.findAll();

        List<ProductApi> productsApi = new ArrayList<>();
        products.forEach(product -> productsApi.add(new ProductApi(product)));

        log.info(FIND_ALL_OK);
        return new ResponseEntity<List>(productsApi, HttpStatus.OK);
    }


    @ApiOperation(value = "Obtiene un Producto", response = ProductApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = FIND_ONE_OK),
            @ApiResponse(code = 404, message = PRODUCT_ID_NOT_EXIST)
    })
    @GetMapping(path = "{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOne(
            @ApiParam(name="id", value = "ID del producto a obtener", required = true)
            @PathVariable(value = "id", required = true) Long id) {

        try {

            Product product = productService.findOne(id);

            log.info(FIND_ONE_OK);
            return new ResponseEntity<ProductApi>(new ProductApi(product), HttpStatus.OK);

        } catch (NotFoundException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Crea un producto", response = ProductApi.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = ADD_CODE_OK),
            @ApiResponse(code = 406, message = PRODUCT_ID_MUST_BE_NULL)})
    @PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(
            @ApiParam(name="Product", value = "Producto a crear", required = true)
            @RequestBody ProductApi productApi) {

        try {

            Product product = productService.add(new Product(productApi));

            log.info(ADD_CODE_OK);
            return new ResponseEntity<>(new ProductApi(product), HttpStatus.CREATED);

        } catch (GenericProductException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ApiOperation(value = "Actualiza un producto", response = ProductApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = UPDATE_CODE_OK),
            @ApiResponse(code = 406, message = PRODUCT_TO_UPDATE_WITHOUT_CHANGES),
            @ApiResponse(code = 404, message = PRODUCT_ID_NOT_EXIST)
    })
    @PutMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @ApiParam(name= "Product", value = "Producto a actualizar", required = true)
            @RequestBody ProductApi productApi) {

        try {

            Product productUpdated = productService.update(new Product(productApi));

            log.info(UPDATE_CODE_OK);
            return new ResponseEntity<>(new ProductApi(productUpdated), HttpStatus.OK);

        } catch (NotFoundException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);

        } catch (GenericProductException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @ApiOperation(value = "Elimina un producto", response = MessageApi.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = DELETE_CODE_OK),
            @ApiResponse(code = 404, message = PRODUCT_ID_NOT_EXIST)
    })
    @DeleteMapping(path = "delete/{id:^[0-9]*$}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(
            @ApiParam(name="id", value = "ID del Producto a eliminar", required = true)
            @PathVariable(value="id", required = true) Long id) {

        try {

            productService.delete(id);

            log.info(DELETE_CODE_OK);
            return new ResponseEntity<>(new MessageApi(DELETE_CODE_OK), HttpStatus.OK);

        } catch (NotFoundException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(new MessageApi(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }


}
