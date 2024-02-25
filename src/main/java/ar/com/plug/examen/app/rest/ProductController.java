package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> sales = this.productService.getAll();
        return ResponseEntity.ok(sales);
    }

    @PostMapping(path = "")
    public ResponseEntity<Product> insert(@RequestBody Product product){
        Product newProduct = this.productService.saveOrUpdate(product);
        return ResponseEntity.ok(newProduct);
    }

}
