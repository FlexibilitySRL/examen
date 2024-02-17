package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    /**
     * A description of the entire Java function.
     *
     * @param  productApi	description of parameter
     * @return         	description of return value
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ProductDto addProduct(@RequestBody ProductApi productApi) {
        ProductDto product = productService.addProduct(productApi);
        log.info("ProductController :: addProduct :: Product added {}", product);
        return product;
    }

    /**
     * Find all products.
     *
     * @return         list of product DTOs
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAll() {
        List<ProductDto> products = productService.findAll();
        log.info("ProductController :: findAll :: FindAll {}", products.size());
        return products;
    }

    /**
     * Find a product by ID.
     *
     * @param  id   The ID of the product to find
     * @return      The product DTO found by the ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") Long id) {
        ProductDto product = productService.findProductById(id);
        if (Objects.isNull(product)) {
            log.error("ProductController :: findProductById :: Product not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ProductController :: findProductById :: Product found {}", product);
        return ResponseEntity.ok(product);
    }

    /**
     * updateProduct function updates a product with the given id using the provided productApi
     *
     * @param  id        the id of the product to update
     * @param  productApi the productApi object containing the updated product information
     * @return          the updated productDto object
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductApi productApi) {
        ProductDto productUpdated = productService.updateProduct(id, productApi);
        if (Objects.isNull(productUpdated)) {
            log.error("ProductController :: updateProduct :: Product not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ProductController :: updateProduct :: Product was updated {}", productUpdated);
        return ResponseEntity.ok(productUpdated);
    }

    /**
     * Delete a product by ID.
     *
     * @param  id   the ID of the product to delete
     * @return      the deleted product DTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id) {
        ProductDto productDeleted = productService.deleteProduct(id);
        if (Objects.isNull(productDeleted)) {
            log.error("ProductController :: deleteProduct :: Product not found");
            return ResponseEntity.notFound().build();
        }
        log.info("ProductController :: deleteProduct :: Product {} was deleted", id);
        return ResponseEntity.ok(productDeleted);
    }
}
