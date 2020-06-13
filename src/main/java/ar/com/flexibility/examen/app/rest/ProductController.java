package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/product")
public class ProductController {
    
    @Autowired
    ProductService service;
    
    @GetMapping(path = "/{productId}")
    public Product get(@PathVariable Long productId) throws NotFoundException {
        Product product = service.read(productId);
        if (product == null) {
            throw new NotFoundException();
        }
        return product;
    }
    
    @DeleteMapping(path = "/{productId}")
    public void delete(@PathVariable Long productId) throws NotFoundException {
        try {
            service.delete(productId);
        }
        catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) throws BadRequestException {
        Product created = service.create(product);
        if (created == null) {
            throw new BadRequestException();
        }
        return created;
    }

    @PostMapping
    public Product modify(@RequestBody Product product) throws BadRequestException {
        Product updated = service.update(product);
        if (updated == null) {
            throw new BadRequestException();
        }
        return updated;
    }
}
