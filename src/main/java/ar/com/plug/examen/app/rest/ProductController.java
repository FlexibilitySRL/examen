package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<ProductApi> listProducts() {
        return productService.listAll();
    }

    @GetMapping(Paths.FIND_BY_ID)
    public ProductApi findById(@PathVariable Long id) throws NotFoundException {
    	return productService.findById(id);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public List<ProductApi> findByName(@PathVariable String name) throws NotFoundException {
    	return productService.findByName(name);
    }
    
    @PostMapping()
    public ProductApi save(@RequestBody ProductApi product) throws BadRequestException {
    	return productService.save(product);
    }

	@DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        productService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductApi update( @RequestBody ProductApi productApi) throws NotFoundException, BadRequestException {
    	return productService.update(productApi);
    }
}
