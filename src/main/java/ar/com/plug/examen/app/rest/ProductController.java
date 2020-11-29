/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msulbara
 */
@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Set<Product>> getProducts() {
        Set<Product> products = productService.findAll();

        LOGGER.info("complete products size is: {}", products.size());

        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

        Product product = new Product();
        product.setId(id);
        try {
            product = productService.findById(id);
            return ResponseEntity.ok().body(product);
        } catch (RuntimeException ex) {
            LOGGER.info("no product for id: {}", id);
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
            path = "/product/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok().body(savedProduct);
    }

    @PutMapping(
            path = "/product/update",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            Product auxProduct = productService.update(product);
            return ResponseEntity.ok().body(auxProduct);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("product does not exists", HttpStatus.NOT_FOUND);
        }
    }

}
