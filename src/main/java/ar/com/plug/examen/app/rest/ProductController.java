package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.aspect.LogginAspect;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Purchase API")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping(value = "/products")
    @LogginAspect
    @ApiOperation(value = "Get the products list", notes = "Return the list of products" )
    public ResponseEntity<?> getProducts(){
        List<Product> products = this.service.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/product/{id}")
    @LogginAspect
    @ApiOperation(value = "Find a product", notes = "Return a product by Id")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        Product product = null;
        try {
            product = this.service.findById(id);
        } catch (ProductDoesNotExistException e) {
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(product);
    }

    @PostMapping(path="/product", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @LogginAspect
    @ApiOperation(value = "Add a product", notes = "Return the product saved passed by param" )
    public ResponseEntity<?> createProduct(@RequestBody Product aProduct){
        Product product = this.service.saveProduct(aProduct);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(path = "/product/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @LogginAspect
    @ApiOperation(value = "Update a product", notes = "Return the product updated referenced by id" )
    public ResponseEntity<?> updateProduct(@RequestBody Product aProduct){
        Product product = null;
        try {
            product = this.service.updateProduct(aProduct);
        } catch (ProductDoesNotExistException e) {
            return new ResponseEntity<>("The product does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(product);
    }


    @DeleteMapping(path="/product/delete/{id}")
    @LogginAspect
    @ApiOperation(value = "Delete a product", notes = "Delete the product referenced by id" )
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
