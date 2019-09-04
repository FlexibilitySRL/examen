package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.base.BaseResponse;
import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.model.PurchaseOrderStatus;
import ar.com.flexibility.examen.domain.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-order")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, purchaseOrderService.findAll());
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, purchaseOrderService.findById(id));
		return new ResponseEntity<>(response, response.getStatusCode());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PurchaseOrderDTO dto) {
		if (dto.getStatus() == null) {
			dto.setStatus(PurchaseOrderStatus.PENDING);
		}
		BaseResponse<?> response = new BaseResponse<>(HttpStatus.OK, purchaseOrderService.save(dto));
		logger.info("Orden de compra registrada correctamente.");
		return new ResponseEntity<>(response, response.getStatusCode());
	}

}
