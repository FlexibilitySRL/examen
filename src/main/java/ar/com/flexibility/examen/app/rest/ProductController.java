package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductApi> getProduct(@PathVariable Long id) {
        log.debug("Request para obtener producto : {}", id);

        Product product = service.findById(id);
        if (product == null) {
            log.debug("no existe id: {}", id);
            return new ResponseEntity<ProductApi>(ProductApi.toApi(null), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductApi>(ProductApi.toApi(product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Request para borrar producto: {}", id);
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
    @PostMapping("/products")
    public ResponseEntity create(@Valid @RequestBody ProductApi productApi) {
        return ResponseEntity.ok(service.create(productApi.toEntity()));
    }
    */

    @PostMapping("/products")
    public ResponseEntity<ProductApi> createProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("Request para grabar producto : {}", productApi);
        /*
        if (productApi.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }
        */

        Product result = service.create(productApi.toEntity());
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
               .body(ProductApi.toApi(result));
    }

    @PutMapping("/products")
    public ResponseEntity<ProductApi> updateProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("Request para actualizar producto : {}", productApi);

        if (productApi.getId() == null) {
            return createProduct(productApi);
        }

        Product result = service.update(productApi.toEntity());
        return ResponseEntity.ok().body(ProductApi.toApi(result));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductApi>> getAllProducts(){
        log.info("Get de todos los productos");

        List<Product> products = service.findAll();
        List<ProductApi> lista = new LinkedList<>();
        for (Product product : products)
        {
            lista.add(ProductApi.toApi(product));
        }

        return new ResponseEntity<List<ProductApi>>(lista, HttpStatus.OK);


    }
}
