package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.dto.ClientDTO;
import ar.com.flexibility.examen.domain.dto.ExistentPurchaseOrderDTO;
import ar.com.flexibility.examen.domain.dto.NewPurchaseOrderDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.service.PurchaseOrderService;

@RestController
@RequestMapping(path = "/")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@GetMapping("purchaseOrders")
	public ResponseEntity<List<ObjectDTO<ExistentPurchaseOrderDTO>>> listPurchaseOrders(@RequestBody PageRequestDTO pageRequestDTO) {
		return new ResponseEntity<List<ObjectDTO<ExistentPurchaseOrderDTO>>>(this.purchaseOrderService.listPurchaseOrders(pageRequestDTO), HttpStatus.OK);
	}
	
	@GetMapping("purchaseOrder/{id}")
	public ResponseEntity<ExistentPurchaseOrderDTO> getPurchaseOrder(@PathVariable("id") Long id) {
		return new ResponseEntity<ExistentPurchaseOrderDTO>(this.purchaseOrderService.getPurchaseOrder(id), HttpStatus.OK);
	}
	
	@PostMapping("purchaseOrder")
	public ResponseEntity<Long> addPurchaseOrder(@RequestBody NewPurchaseOrderDTO purchaseOrderDTO) {
		return new ResponseEntity<Long>(this.purchaseOrderService.addPurchaseOrder(purchaseOrderDTO), HttpStatus.OK);
	}
	
	@PostMapping("purchaseOrder/{id}/transaction")
	public ResponseEntity<Void> approvePurchaseOrder(@PathVariable("id") Long id) {
		this.purchaseOrderService.approvePurchaseOrder(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("purchaseOrder/{id}/transaction")
	public ResponseEntity<Long> getPurchaseTransaction(@PathVariable("id") Long purchaseOrderId) {
		return new ResponseEntity<Long>(this.purchaseOrderService.getPurchaseTransaction(purchaseOrderId), HttpStatus.OK);
	}
	
	@DeleteMapping("purchaseOrder/{id}")
	public ResponseEntity<Void> removePurchaseOrder(@PathVariable("id") Long id) {
		this.purchaseOrderService.removePurchaseOrder(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
