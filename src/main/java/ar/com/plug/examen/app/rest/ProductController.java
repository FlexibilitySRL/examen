package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.paths.Paths;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = Paths.PRODUCT)
public class ProductController {

	@Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductApi>> listProducts() {
        return new ResponseEntity<>(productService.listAll(), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_ID)
    public ResponseEntity<ProductApi> findById(@PathVariable long id) throws NotFoundException {
    	return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public ResponseEntity<List<ProductApi>> findByName(@PathVariable String name) {
    	return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<ProductApi> save(@RequestBody ProductApi product) throws BadRequestException {
    	return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) throws NotFoundException {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductApi> update( @RequestBody ProductApi productApi) throws NotFoundException, BadRequestException {
    	return new ResponseEntity<>(productService.update(productApi), HttpStatus.ACCEPTED);
    }
}
