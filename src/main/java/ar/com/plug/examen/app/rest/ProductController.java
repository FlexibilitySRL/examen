package ar.com.plug.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ServiceGeneric;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ServiceGeneric<ProductApi,Product> productService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductController (ProductServiceImpl productService) {
        this.productService = productService;
    }
    
    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody ProductApi productApi) throws ValidatorException{	
    	productService.create(productApi);
    	logger.info("Se creo el producto correctamente");
        return new ResponseEntity<>("Se creo el producto correctamente", HttpStatus.OK);
    }
    
    @PutMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProduct(@RequestBody ProductApi productApi) throws ValidatorException, NotExistException{	
    	productService.update(productApi);
    	logger.info("Se modifico el producto correctamente");

        return new ResponseEntity<>("Se modifico el producto correctamente", HttpStatus.OK);
    }
    
    @GetMapping(path = "/{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductApi> findProduct(@PathVariable Long idProduct) throws NotExistException, ValidatorException{	
    	ProductApi product = productService.find(idProduct);
    	logger.info("Se obtuvo el producto correctamente");
    	
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @GetMapping(path = "")
    public ResponseEntity<List<ProductApi>> findProducts() throws NotExistException{	
    	List<ProductApi> products= productService.findAll();
    	logger.info("Se obtuvieron los producto correctamente");

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> deleteProduct(@PathVariable Long idProduct) throws NotExistException, ValidatorException{	
    	productService.delete(idProduct);
    	logger.info("Se elimino el product correctamente");

        return new ResponseEntity<>("Se elimino el product correctamente", HttpStatus.OK);
    }
}
