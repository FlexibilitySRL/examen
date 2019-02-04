package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.service.InvoiceService;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@RestController
@RequestMapping(path = "/private/invoice")
public class InvoiceController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	/**
	 * Get a Invoice by ID
	 * 
	 * @param idInvoice
	 * @return Invoice
	 */
	@GetMapping("/{idInvoice}")
	@ApiOperation(value = "Get a Invoice by ID")
	public ResponseEntity<Invoice> findOne(@PathVariable Long idInvoice) {
		log.info("Getting Invoice by ID: "+idInvoice);
		
		Invoice response = invoiceService.findById(idInvoice);
		
		return new ResponseEntity<Invoice>(response, HttpStatus.OK);
	}
	
	/**
	 * Get All of Invoices by Customer ID
	 * 
	 * @return List<Invoice>
	 */
	@GetMapping("/customer/{idCustomer}")
	@ApiOperation(value = "Get All of Invoices by Customer ID")
	public ResponseEntity<List<Invoice>> findByCustomer(@PathVariable Long idCustomer) {
		log.info("Getting All of Invoices by Customer ID: "+idCustomer);
		
		List<Invoice> invoices = invoiceService.findByCustomer(idCustomer);
		
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	
	//@PutMapping
	public ResponseEntity<Invoice> approve(@RequestBody Invoice invoice) {
		log.info("Approve invoice");
		
		// TODO Not implemented yet
		
		return null;
		
	}
}
