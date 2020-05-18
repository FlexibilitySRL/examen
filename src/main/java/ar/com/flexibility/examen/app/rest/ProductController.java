package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Buscar Productos", response = Product.class)
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllProducts(){
    	LOGGER.info("Servicio de Busqueda de todos los productos");
        return productService.getProducts();
    }
    
    @ApiOperation(value = "Buescar un Productos", response = Product.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de Busqueda del producto con Id: {}", id);
        return productService.getProductById(id);
    }
    
    @ApiOperation(value = "Insertar producto", response = Product.class)
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> insertNewProduct(@RequestBody Product product){
    	LOGGER.info("Servicio de insersion de un nuevo producto");
        return productService.insertProduct(product);
    }
    
    @ApiOperation(value = "Actualizar producto", response = Product.class)
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product product){
    	LOGGER.info("Servicio de actualizacion del producto con id: {}", id);
        return productService.updateProduct(id, product);
    }
    
    @ApiOperation(value = "Borrar producto", response = Product.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de Borrado del producto con id: {}", id);
        return productService.deleteProduct(id);
    }
    
}
