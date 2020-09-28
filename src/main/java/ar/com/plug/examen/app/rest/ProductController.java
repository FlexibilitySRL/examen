package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.response.ApiError;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;
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
        if (!productService.exists(product.getCod())) {
            ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Product already exists",
                    "The Product you attempt to register already exists");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        Product product1 = productService.createProduct(product);

        return new ResponseEntity<>(product1, HttpStatus.CREATED);
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


    @DeleteMapping("{cod}")
    public ResponseEntity<?> deleteProduct (@PathVariable String cod) {
        try {
            productService.deleteProduct(cod);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("{cod}")
    public ResponseEntity<?> getProduct (@PathVariable String cod) {
        Product product = null;
        try {
            product = productService.getProduct(cod);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
