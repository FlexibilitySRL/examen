package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.dto.ProductDto;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    /**
     * Creates a new product.
     *
     * @param product the product to create.
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addProduct(@RequestBody ar.com.flexibility.examen.app.api.Product product) {
        log.info("Entering addProduct {}", product);
        Product entity = mapper.map(product, Product.class);
        productService.save(entity);
        log.info("Leaving addProduct...");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves all products.
     *
     * @return a product's list.
     */
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        log.info("Entering findAllProducts...");
        List<ProductDto> productDtos = productService.findAll().
                stream().map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        log.info("Leaving findAllProducts...");
        return new ResponseEntity(productDtos, HttpStatus.OK);
    }

    /**
     * Modifies a product.
     *
     * @param id      the product's id.
     * @param product the body's request.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id, @RequestBody ar.com.flexibility.examen.app.api.Product product) {
        log.info("Entering updateProduct {}, {} ", id, product);
        Product entity = mapper.map(product, Product.class);
        if (productService.find(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.update(id, entity);
        log.info("Leaving updateProduct ... ", product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a product.
     *
     * @param id the product's id.
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        log.info("Entering deleteProduct {}", id);
        if (productService.find(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        productService.delete(id);
        log.info("Leaving deleteProduct ... ");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
