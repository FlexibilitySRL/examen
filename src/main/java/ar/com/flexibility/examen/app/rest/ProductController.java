package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ProductApi> createProduct(@RequestBody ProductApi productApi) {
        return new ResponseEntity<>(productService.create(productApi), HttpStatus.CREATED);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductApi> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductApi>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
    	productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/updateProduct/{id}")
    public ResponseEntity<ProductApi> updateProduct(@PathVariable Long id, @RequestBody ProductApi productApi) {
        return new ResponseEntity<>(productService.update(id, productApi), HttpStatus.OK);
    }
}
