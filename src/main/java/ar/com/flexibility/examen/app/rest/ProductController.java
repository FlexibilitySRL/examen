package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;

/**
 * 
 * @author hackma
 * @version 0.1 Servicio de productos
 */
@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * Metodo para la creación de productos
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody Product Product) {
		log.info("Entrada de metodo {createProduct}");
		try {
			ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.createProduct(Product),
					HttpStatus.OK);
			log.info("Salida de metodo {createProduct} Producto creado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {createProduct}", e);
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para actualizar al producto por el id
	 * 
	 * @param Product
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Product Product) {
		log.info("Entrada de metodo {updateProduct}");
		try {
			ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.updateProduct(Product),
					HttpStatus.OK);
			log.info("Salida de metodo {updateProduct} Producto actualizado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {updateProduct}", e);
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para eliminar al producto por el id
	 * 
	 * @param Product
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestBody Product Product) {
		log.info("Entrada de metodo {deleteProduct}");
		try {
			ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.deleteProduct(Product),
					HttpStatus.OK);
			log.info("Salida de metodo {deleteProduct} Producto eliminado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {deleteProduct}", e);
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo para listar a todos los productos
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public ResponseEntity<?> findAllProducts() {
		log.info("Entrada de metodo {findAllProduct}");
		try {
			ResponseEntity<List<Product>> responseEntity = new ResponseEntity<List<Product>>(
					productService.findAllProducts(), HttpStatus.OK);
			log.info("Salida de metodo {findAllProduct} Productos encontrados exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {findAllProduct}", e);
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Metodo para encontrar un producto especifico por el id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findProductById(@PathVariable String id) {
		log.info("Entrada de metodo {findProductById}");
		try {
			ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(
					productService.findProductById(Long.valueOf(id)), HttpStatus.OK);
			log.info("Salida de metodo {findProductById} Producto encontrado exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {findProductById}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
