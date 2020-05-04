package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  The ClientController class exposes the CRUD operations for the product model
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.retrieveProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.retrieveProductById(id);

        if (product == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource was created successfully."),
            @ApiResponse(code = 400, message = "Could not update the resource.")
    })
    public ResponseEntity<Product> createProduct(@Valid @NotNull @RequestBody Product product) {
        Product newProduct = productService.addProduct(product);

        if (newProduct == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(newProduct, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 400, message = "Could not update the resource.")
    })
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(updatedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 500, message = "Could not delete the resource.")
    })
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        boolean deletedProduct = productService.deleteProduct(id);

        if (deletedProduct) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
