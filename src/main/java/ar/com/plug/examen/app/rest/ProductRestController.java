package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *
 * @author Joan Rey
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository customerRepository;

    @GetMapping()
    public List<Product> list() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        Product customer = customerRepository.findById(id).get();
        return customer;
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Product input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) {
        Product save = customerRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
}
