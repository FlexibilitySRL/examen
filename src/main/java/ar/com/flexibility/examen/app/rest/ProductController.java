package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.rest.errors.BadRequestAlertException;
import ar.com.flexibility.examen.app.rest.util.HeaderUtil;
import ar.com.flexibility.examen.app.rest.util.PaginationUtil;
import ar.com.flexibility.examen.domain.service.ProductService;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private static final String ENTITY_NAME = "product";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * POST  /products : Create a new product.
     *
     * @param productApi the productApi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productApi,
     * or with status 400 (Bad Request) if the product has already an ID
     *
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/products")
    @Timed
    public ResponseEntity<ProductApi> createProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productApi);
        if (productApi.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductApi result = productService.save(productApi);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /products : Updates an existing product.
     *
     * @param productApi the productApi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productApi,
     * or with status 400 (Bad Request) if the productApi is not valid,
     * or with status 500 (Internal Server Error) if the productApi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/products")
    @Timed
    public ResponseEntity<ProductApi> updateProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productApi);
        if (productApi.getId() == null) {
            return createProduct(productApi);
        }
        ProductApi result = productService.save(productApi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productApi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products")
    @Timed
    public ResponseEntity<List<ProductApi>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<ProductApi> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/{id}")
    @Timed
    public ResponseEntity<ProductApi> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        ProductApi productApi = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productApi));
    }

    /**
     * DELETE  /products/:id : delete the "id" product.
     *
     * @param id the id of the productDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/products/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
