package ar.com.flexibility.examen.app.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.exception.GenericException;

@RestController
@RequestMapping(path = "/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@GetMapping(path = "/")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Seller>> findAll() throws GenericException {
		return new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Seller> find(@PathVariable("id") Long id) throws GenericException {
		return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
	}

	@PostMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Seller> save(@Valid @NotNull @RequestBody Seller seller) throws GenericException {
		return new ResponseEntity<>(sellerService.create(seller), HttpStatus.CREATED);
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Seller> update(@PathVariable("id") Long id, @Valid @NotNull @RequestBody Seller seller)
			throws GenericException {
		seller.setId(id);
		return new ResponseEntity<>(sellerService.update(seller), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Seller> remove(@PathVariable("id") Long id) throws GenericException {
		sellerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}