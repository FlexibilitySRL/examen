package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.ErrorResponse;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "Product service", description = "Product CRUD")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductApi productApi) {
        Product product = productService.save(new Product(productApi.getDescription(), productApi.getPrice()));
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @Valid @RequestBody ProductApi productApi) {

        Product product = productService.findById(id);
        product.setDescription(productApi.getDescription());
        product.setPrice(productApi.getPrice());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException() {
        return ResponseEntity.notFound().build();
    }

}
