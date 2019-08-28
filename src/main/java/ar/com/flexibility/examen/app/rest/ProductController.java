package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    //@Autowired
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    /**
     * GET /products/id obtiene el producto indicado por id
     * @param id del producto a buscar
     * @return ResponseEntity con status 200 si el producto existe o 404 si no
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductApi> getProduct(@PathVariable Long id) {
        log.debug("Request para obtener producto : {}", id);

        Product product = service.findById(id);
        if (product == null) {
            log.debug("no existe id: {}", id);
            return new ResponseEntity<ProductApi>(toApi(null), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProductApi>(toApi(product), HttpStatus.OK);
    }

    /**
     * DELETE  /products/id borra el producto indicado por id.
     * @param id del producto a borrar
     * @return ResponseEntity with status 204 
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Request para borrar producto: {}", id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    /**
     * POST /products crea producto nuevo
     * @param productApi
     * @return ResponseEntity con status 201
     * @throws URISyntaxException
     */
    @PostMapping("/products")
    public ResponseEntity<ProductApi> createProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("Request para crear producto : {}", productApi);
        Product result = service.create(toEntity(productApi));
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
               .body(toApi(result));
    }

    
    /**
    * PUT  /products actualiza un producto existente
    *
    * @param productApi el productApi a actualizar
    * @return ResponseEntity con status 200 (OK) o
    *         con status 404 si el productApi no tiene id valido,
    * 
    */
    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductApi productApi) throws URISyntaxException {
        log.debug("Request para actualizar producto : {}", productApi);

        if (productApi.getId() == null) {
        	log.error("el producto no existe");
			return new ResponseEntity<String>("El producto no existe", HttpStatus.NOT_FOUND);
        }
        
        Product result = null;
        try {
        	result = service.update(toEntity(productApi));
        }catch (Exception e)
        {
        	log.error("el producto no existe");
			return new ResponseEntity<String>("El producto no existe", HttpStatus.NOT_FOUND);
		}
                
        return ResponseEntity.ok().body(toApi(result));
    }

    /**
     * GET  /products obtiene todos los productos
     * @return ResponseEntity con status 200
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductApi>> getAllProducts(){
        log.info("Get de todos los productos");

        List<Product> products = service.findAll();
        List<ProductApi> lista = new ArrayList<ProductApi>();
        for (Product product : products)
        {
            lista.add(toApi(product));
        }

        return new ResponseEntity<List<ProductApi>>(lista, HttpStatus.OK);


    }
    
    private Product toEntity(ProductApi productApi) {
    	
    	Product product = new Product();
        product.setId(productApi.getId());
        product.setName(productApi.getName());
        product.setDescription(productApi.getDescription());
        product.setPrice(productApi.getPrice());
        product.setStock(productApi.getStock());
        return product;
    }
    
    private ProductApi toApi(Product product) {
        ProductApi productApi = new ProductApi();
        if(product != null) {
            productApi.setId(product.getId());
            productApi.setName(product.getName());
            productApi.setDescription(product.getDescription());
        }
        return productApi;
    } 
}
