package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/product")
public class ProductController {
    
    @Autowired
    ProductService service;
    
    @RequestMapping(path = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> get(@PathVariable Long productId) {
        Product product = service.read(productId);
        if (product == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }
    
    @RequestMapping(path = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long productId) {
        service.delete(productId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = service.create(product);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> modify(@RequestBody Product product) {
        Product updated = service.update(product);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
