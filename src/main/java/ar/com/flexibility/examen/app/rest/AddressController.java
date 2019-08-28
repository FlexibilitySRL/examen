package ar.com.flexibility.examen.app.rest;

import java.util.Collections;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;

	// Get paged addresses ?page=0&size=1
	@GetMapping("/api/addresses")
	public ResponseEntity<Page<Address>> getAddresses(Pageable pageable) {
		Page<Address> addresses = addressService.getAddresses(pageable);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	// Get customer's paged addresses ?page=0&size=1
	@GetMapping("/api/customers/{customerId}/addresses")
	public ResponseEntity<Page<Address>> getAddresses(@PathVariable("customerId") Long customerId, Pageable pageable) throws EntityNotFoundException {
		Page<Address> addresses = addressService.getAddresses(customerId, pageable);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	// Get a customer address
	@GetMapping("/api/customers/{customerId}/addresses/{addressId}")
	public ResponseEntity<Address> getAddress(@PathVariable("customerId") Long customerId,
											  @PathVariable("addressId") Long addressId) throws EntityNotFoundException {
		Address address = addressService.getAddress(customerId, addressId);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	// Create an address for a customer
	@PostMapping(path = "/api/customers/{customerId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> createAddress(@PathVariable("customerId") Long customerId,
												 @Valid @RequestBody Address address) throws EntityNotFoundException {
		Address createdAddress = addressService.createAddress(customerId, address);
		return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
	}

	// Update a customer's address 
	@PatchMapping(path = "/api/customers/{customerId}/addresses/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> updateAddress(@PathVariable("customerId") Long customerId,
												 @PathVariable("addressId") Long addressId,
												 @Valid @RequestBody Address address) throws EntityNotFoundException, IllegalArgumentException {
		Address createdAddress = addressService.updateAddress(customerId, addressId, address);
		return new ResponseEntity<>(createdAddress, HttpStatus.OK);
	}

	// Delete a customer's address
	@DeleteMapping(path = "/api/customers/{customerId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable("customerId") Long customerId,
															 @PathVariable("addressId") Long addressId) throws EntityNotFoundException, IllegalArgumentException {
		String message = addressService.deleteAddress(customerId, addressId);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
