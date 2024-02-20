package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product p) throws ProductException {
        Product product = productService.addProduct(p);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product p) throws ProductException {
        Product product = productService.updateProduct(p);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Product>> viewAllProduct() throws ProductException {
        return new ResponseEntity<List<Product>>(productService.viewAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<Product> viewProductById(@PathVariable("productId") Integer productId) throws ProductException {
        return new ResponseEntity<Product>(productService.viewProduct(productId), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Product> removeProductById(@PathVariable("productId") Integer productId)
            throws ProductException {
        return new ResponseEntity<Product>(productService.removeProduct(productId), HttpStatus.OK);
    }
}
