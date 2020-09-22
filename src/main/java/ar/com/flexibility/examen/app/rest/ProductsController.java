package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import ar.com.flexibility.examen.domain.service.ProcessProductService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@RestController
@RequestMapping(path = "/products")
public class ProductsController {
	
	private final Logger LOG = LoggerFactory.getLogger(PurchasesTransactionsController.class);
	
	@Autowired
	private ProcessMessageService messageService;
	
	@Autowired
	@Qualifier("ProcessProductServiceImpl")
	private ProcessProductService productService;	

	
	@GetMapping("/get-product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(value = "id") Long id){
		Product client = new Product();
		try {
			client = productService.getProductById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}	
		return ResponseEntity.ok().body(client);
	}
	
	@GetMapping("/get-all-product")
	public ResponseEntity<?> getAllProduct(){
		List<Product> listProduct = new ArrayList<>();
		try {
			listProduct = productService.getProducts();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);			
		}
		return ResponseEntity.ok().body(listProduct);
	}
	
	@PostMapping("/save-product")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		Message message = new Message(); 
		Boolean resp = false;
		try {
			resp = productService.saveProduct(product);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PutMapping("/update-product/{id}")
	public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id, @RequestBody Product product) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = productService.updateProduct(product, id);
			if(resp) {
				message = messageService.processMessage("Se ha guardando la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-product/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) {
		Message message = new Message(); 
		Boolean resp = false;		
		try {
			resp = productService.deleteProduct(id);
			if(resp) {
				message = messageService.processMessage("Se ha eliminado la información.");
			}else {
				message = messageService.processMessage("Ha ocurriodo un error guardando la información");
			}						
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);	
		}
		return new ResponseEntity<Message>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}

}
