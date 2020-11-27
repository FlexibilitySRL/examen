package ar.com.plug.examen.app.rest;

 

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
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

import ar.com.plug.examen.app.api.CustomerDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired
	private Mapper mapper;
    
  
    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
 
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create successfully."),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> createProduct(@RequestBody ProductDto dto)
    {
    	Product productNew = mapper.map(dto, Product.class);

    	return new ResponseEntity<>(productService.create(productNew), HttpStatus.OK);
    }

    
    @GetMapping(path = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<List<Product>> getProductsAll() {
    	
    	List<Product> products = productService.getProducts();
        
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    
    
    
	@GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
    	
    	Optional<Product> product = productService.getProductById(id);

        if (!product.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }
    
	
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request updated successfully."),
    })
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id,@RequestBody ProductDto dto) {
    	
    	
    	Product productToUpdate= mapper.map(dto, Product.class);
 

    	return new ResponseEntity<>(productService.update(id,productToUpdate), HttpStatus.OK);
 
     }

    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
    })
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) {
    	
    	productService.delete(id);
 
        return new ResponseEntity<Product>( HttpStatus.OK);
    }
    
    
}
