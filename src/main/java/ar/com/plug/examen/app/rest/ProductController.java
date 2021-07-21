package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    @GetMapping("/api/v1/products")
    public Page<Product> getProducts(@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "name", direction = Sort.Direction.DESC) }) Pageable pageable) {

        return productService.findAll(pageable);
    }

    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") long productId) {

        Product oProduct = productService.getById(productId);
        if (oProduct != null)
            return new ResponseEntity<>(oProduct, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Product result = productService.save(product);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/api/v1/products")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Product result = productService.update(product);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
