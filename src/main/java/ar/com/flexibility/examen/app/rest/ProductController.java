package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductApi> retrieveProduct(@RequestBody ProductApi productApi) {
        return new ResponseEntity<>(productService.create(productApi), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductApi> retrieveProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.retrieve(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductApi>> listProducts() {
        return new ResponseEntity<>(productService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductApi> updateProduct(@PathVariable Long id, @RequestBody ProductApi productApi) {
        return new ResponseEntity<>(productService.update(id, productApi), HttpStatus.OK);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
