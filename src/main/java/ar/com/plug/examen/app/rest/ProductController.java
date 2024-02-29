package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> sales = this.productService.getAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Product product = this.productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product){
        Product newProduct = this.productService.saveOrUpdate(product);
        return ResponseEntity.ok(newProduct);
    }

    @PatchMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        Product newProduct = this.productService.saveOrUpdate(product);
        return ResponseEntity.ok(newProduct);
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        this.productService.delete(id);
    }

}
