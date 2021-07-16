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
import ar.com.plug.examen.domain.model.SellerDTO;
import ar.com.plug.examen.domain.service.ISellerRepo;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	
	
	@Autowired
	private ISellerRepo sellerService;
	
	
	/**
	 * Return all sellers
	 * @return List<SellerDTO>
	 */
	@GetMapping("/")
	public ResponseEntity<List<SellerDTO>> getSellers() {
		return new ResponseEntity<>(this.sellerService.findAll(), HttpStatus.OK);
	}
	
	
	/**
	 * Find one seller by id
	 * @param id -> long
	 * @return SellerDTO
	 * @throws ResourceNotFoundError
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SellerDTO> getSellerByID(@PathVariable long id) throws ResourceNotFoundError {
	    return new ResponseEntity<SellerDTO>(this.sellerService.findSellerByID(id), HttpStatus.OK);
	}
	
	/**
	 * Save one seller.
	 * @param request -> SellerDTO
	 * @return SellerDTO
	 * @throws BadRequestError 
	 */
	@PostMapping("/")
	public ResponseEntity<SellerDTO> save(@RequestBody SellerDTO request) throws BadRequestError {
		return new ResponseEntity<SellerDTO>(this.sellerService.save(request), HttpStatus.CREATED);
	}
	     
	/**
	 * Delete a seller by id
	 * @param id -> long
	 * @return void
	 * @throws ResourceNotFoundError
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) throws ResourceNotFoundError {
		this.sellerService.delete(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Update a seller
	 * @param request -> SellerDTO
	 * @return SellerDTO
	 * @throws ResourceNotFoundError
	 * @throws BadRequestError 
	 */
	@PutMapping("/")
	public ResponseEntity<SellerDTO> update(@RequestBody SellerDTO request) throws ResourceNotFoundError, BadRequestError {
		return new ResponseEntity<SellerDTO>(this.sellerService.update(request), HttpStatus.OK);
	}

}
