package ar.com.flexibility.examen.app.rest;

import java.util.HashMap;

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

import ar.com.flexibility.examen.domain.base.BaseResponse;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, productService.findAll());
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, productService.findById(id));
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, productService.save(dto));
		logger.info("Producto registrado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ProductDTO dto, @PathVariable Long id) {
		dto.setId(id);
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, productService.save(dto));
		logger.info("Producto actualizado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("deleted", productService.delete(id));
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, result);
		logger.info("Producto borrado correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

}
