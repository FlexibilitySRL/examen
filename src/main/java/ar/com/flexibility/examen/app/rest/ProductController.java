package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.exception.FlexibilityException;
import ar.com.flexibility.examen.app.exception.FlexibilityNotFoundException;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(tags = "Products API")
@RestController
@RequestMapping("/api")
public class ProductController extends BaseController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * {@code POST  /products} : Create a new customer.
     *
     * @param productDTO the customerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDTO,
     * or with status {@code 400 (Bad Request)} if the productDTO has already an ID.
     */
    @ApiOperation("Create a Product")
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> create(@ApiParam("A product") @Valid @RequestBody ProductDTO productDTO) {
        log.debug("REST request to update Product : {}", productDTO);

        if (!ObjectUtils.isEmpty(productDTO.getId())) {
            throw new FlexibilityException("A new Product cannot already have an ID");
        }
        return ResponseEntity.ok(productService.save(productDTO));
    }

    /**
     * {@code PUT  /products} : Updates an existing product.
     *
     * @param productDTO the contactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productDTO,
     * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productDTO couldn't be updated.
     */
    @ApiOperation("Update a Product")
    @PutMapping("/products")
    public ResponseEntity<ProductDTO> update(@ApiParam("A product") @Valid @RequestBody ProductDTO productDTO) {
        log.debug("REST request to update Product : {}", productDTO);

        if (ObjectUtils.isEmpty(productDTO.getId())) {
            throw new FlexibilityException("ID cannot be null");
        }

        return ResponseEntity.ok(productService.save(productDTO));
    }

    /**
     * {@code GET  /products} : get all the products.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @ApiOperation("Get all Products")
    @GetMapping("/products")
    public List<ProductDTO> findAll() {
        log.debug("REST request to get all Customers");
        return productService.findAll();
    }

    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDTO, or with status
     * {@code 404 (Not Found)}.
     */
    @ApiOperation("Get a Product")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findById(@ApiParam("Product Id") @PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        return productService.findOne(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new FlexibilityNotFoundException("ID not found"));
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     *
     * @param id the id of the productDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @ApiOperation("Remove a Product")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@ApiParam("Product Id") @PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
