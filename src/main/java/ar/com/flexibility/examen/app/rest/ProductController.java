package ar.com.flexibility.examen.app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;

@Api(tags = "Products API")
@RestController
@RequestMapping(path = "/products")
public class ProductController {
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
    private ProductService productService;

	
	/**
     * {@code POST  /create} : Create a new customer.
     *
     * @param product the product to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDTO,
     * or with status {@code 400 (Bad Request)} if the product has already an ID.
     */
    @PostMapping("/create")
    @ApiOperation(value="Create a product",
	notes="Service used to create a product given its name, description, price and stock")
    public ResponseEntity<Product> create(@ApiParam("A product") @Valid @RequestBody Product product) {
        
    	log.debug("REST request to update Product : {}", product);

        if (!ObjectUtils.isEmpty(product.getId())) {
            throw new RuntimeException("A new Product cannot create, already have an ID");
        }
        return ResponseEntity.ok(productService.save(product));
    }
    
    /**
     * {@code PUT  /products} : Updates an existing product.
     *
     * @param product the product to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated product,
     * or with status {@code 400 (Bad Request)} if the product is not valid,
     * or with status {@code 500 (Internal Server Error)} if the product couldn't be updated.
     */
    @ApiOperation(value="Update a Product" , notes="Service used to update a product")
    @PutMapping("/update")
    public ResponseEntity<Product> update(@ApiParam("A product") @Valid @RequestBody Product product) {
        log.debug("REST request to update Product : {}", product);

        if (ObjectUtils.isEmpty(product.getId())) {
            throw new RuntimeException("ID cannot be null");
        }

        return ResponseEntity.ok(productService.save(product));
    }
    
    /**
     * {@code GET  /product/:id} : get the "id" product.
     *
     * @param id the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    @ApiOperation(value="Get a product",
	notes="Service used to get a product")
    public ResponseEntity<Product> getProduct(@ApiParam(value = "Product Id", required = true) @PathVariable Long id) {
    	
    	log.debug("REST request to get Product : {}", id);
    	
    	Product product = productService.getProductById(id);
    	
        return ResponseEntity.ok(product);
    }
    
    /**
     * {@code DELETE  /products/delete/:id} : delete the "id" product.
     *
     * @param id the id of the product to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @ApiOperation("Remove a Product")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@ApiParam(value = "Product Id", required = true) @PathVariable Long id) {
        
    	log.debug("REST request to delete Product : {}", id);

        productService.deleteProduct(id);
        
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Get all the products.
     *
     * @return the list of entities.
     */
    @GetMapping("")
    @Transactional(readOnly = true)
    @ApiOperation(value="Get all products",
	notes="Service used to get all products")
    public ResponseEntity<List<Product>> findAll() {
    	
    	log.debug("Request to get all Products");
    	
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
