package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.DTO.ProductDTO;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.mapper.ProductMapper;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    /**
     * Agrega un nuevo producto.
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductApi productApi) {
        ProductDTO productAdded = productService.addProduct(mapper.asDTO(productApi));
        log.info("Producto agregado con éxito: {}", productApi.getName());
        return ResponseEntity.ok(productAdded);
    }

    /**
     * Actualiza la información de un producto.
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductApi productApi) {
        ProductDTO productUpdated = productService.updateProduct(productId, mapper.asDTO(productApi));
        log.info("Producto actualizado con éxito: {}", productApi.getName());
        return ResponseEntity.ok(productUpdated);
    }

    /**
     * Elimina un producto.
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        log.info("Producto eliminado con éxito: {}", productId);
        return ResponseEntity.ok().build();
    }

    /**
     * Obtiene la lista de todos los productos.
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        log.info("Consulta exitosa de todos los productos.");
        return ResponseEntity.ok(products);
    }

    /**
     * Obtiene la lista de todos los productos.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID productId) {
        Optional<ProductDTO> product = productService.getProductById(productId);
        log.info("Consulta exitosa de todos los productos.");
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
