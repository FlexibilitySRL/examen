package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("save")
    public ResponseEntity<Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @PutMapping("update")
    public ResponseEntity<Product> update(@RequestBody Product product){

        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){

        return productService.getById(productId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/stockgreaterthan/{stock}")
    public ResponseEntity<List<Product>> findByStokGreatherThan(@PathVariable("stock") int stock) {

        return productService.findByStokGreatherThan(stock)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/pricelessthan/{price}")
    public ResponseEntity<List<Product>> findByStokGreatherThan(@PathVariable("price") long price) {

        return productService.findByPriceIsLessthan(price)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }


}
