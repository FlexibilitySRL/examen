package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.response.ApiError;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.exception.ProductExistsException;
import ar.com.flexibility.examen.domain.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;




    @PostMapping("")
    public ResponseEntity<?> createProduct (@RequestBody Product product) {

        try {
            Product product1 = productService.createProduct(product);
        } catch (ProductExistsException e) {
            ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Product already exists",
                    "The Product you attempt to register already exists");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @PutMapping("")
    public ResponseEntity<?> updateProduct (@RequestBody Product product) {

        try {
            productService.updateProduct(product);
        } catch (ProductNotFoundException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Product not found",
                    "The Product you attempt to access is not registered");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct (@PathVariable Long id) {

        try {
            productService.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getProduct (@PathVariable Long id) {

        Product product = null;

        try {
            product = productService.getProductById(id);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
