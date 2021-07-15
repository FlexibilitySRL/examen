package ar.com.plug.examen.app.rest;

import java.util.List;

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

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.service.IProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductRepo productService;
	
	
	/**
	 * Find all products
	 * @return List<ProductDTO>
	 */
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> findAll() {
		return new ResponseEntity<List<ProductDTO>>(this.productService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Return one product by id
	 * @param id -> Long
	 * @return ProductDTO
	 * @throws ResourceNotFoundError
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findProductByID(@PathVariable Long id) throws ResourceNotFoundError {
		return new ResponseEntity<ProductDTO>(this.productService.findClientById(id),HttpStatus.OK);
	}
	
	/**
	 * Save one product.
	 * @param request -> ProductDTO
	 * @return ProductDTO
	 * @throws BadRequestError
	 */
	@PostMapping("/")
	public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO request) throws BadRequestError {
		return new ResponseEntity<ProductDTO>(this.productService.save(request), HttpStatus.CREATED);
	}
	
	/**
	 * Update a product
	 * @param request -> ProductDTO
	 * @return ProductDTO
	 * @throws ResourceNotFoundError
	 * @throws BadRequestError
	 */
	@PutMapping("/")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO request) throws ResourceNotFoundError, BadRequestError {
		return new ResponseEntity<ProductDTO>(this.productService.update(request), HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> delete(long id) throws ResourceNotFoundError {
		this.productService.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
