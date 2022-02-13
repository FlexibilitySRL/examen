package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity getProducts() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Client> createProduct(@Valid @RequestBody Product request) {
//        Product product = productService.save(request);
//        return new ResponseEntity<>(request/, HttpStatus.OK);
        return null;
    }
}
