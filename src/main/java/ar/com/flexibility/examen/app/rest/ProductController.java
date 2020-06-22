package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductApi> getProduct(@PathVariable Long productId) {
        log.info("Request to GET productId: " + productId);
        return ResponseEntity.status(HttpStatus.OK).body(productService.get(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductApi>> listProducts() {
        log.info("Request to GET all the products");
        return ResponseEntity.status(HttpStatus.OK).body(productService.all());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductApi> createProduct(@Valid @RequestBody ProductApi productApi) {
        log.info("Request to POST new product: " + productApi);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productApi));
    }

    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductApi> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductApi productApi) {
        log.info("Request to PUT with productId" + productId + "  with product: " + productApi);
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(productId, productApi));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
        log.info("Request to DELETE with productId" + productId);
        productService.remove(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
