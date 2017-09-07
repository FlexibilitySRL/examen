package ar.com.flexibility.examen.app.rest;

import java.util.List;

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

import ar.com.flexibility.examen.domain.model.InvoiceDetail;
import ar.com.flexibility.examen.domain.service.InvoiceDetailService;

/**
 * 
 * @author hackma
 * @version 0.1
 * Servicio de Clientes 
 */
@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createInvoice(@RequestBody InvoiceDetail invoice) {
		System.out.println("#invoice " + invoice);
		log.info("Entrada de metodo {createInvoice}" );
		try {
			ResponseEntity<InvoiceDetail> responseEntity = new ResponseEntity<InvoiceDetail>(
					invoiceDetailService.createInvoice(invoice), HttpStatus.OK);
			log.info("Salida de metodo {createInvoice} factura creada exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {createInvoice}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findInvoice(@PathVariable String id) {
		log.info("Entrada de metodo {findInvoice}");
		try {
			InvoiceDetail invoice = new InvoiceDetail();
			if(id != null) {
				invoice.setId(Long.valueOf(id));
			}
			ResponseEntity<List<InvoiceDetail>> responseEntity = new ResponseEntity<List<InvoiceDetail>>(
					invoiceDetailService.findInvoice(invoice), HttpStatus.OK);
			log.info("Salida de metodo {findInvoice} factura encontrada exitosamente");
			return responseEntity;
		} catch (Exception e) {
			log.error("Ha ocurrido un error en el metodo {findInvoice}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> findInvoice() {
		return findInvoice(null);
	}
}

/** Mensajeria
 *  {
        "id": 1,
        "invoice": {
            "id": 1,
            "customer": {
                "id": 1,
                "firstName": "jhonatan",
                "lastName": "perez",
                "lastname": "perez"
            },
            "date": 1504760400000
        },
        "product": {
            "id": 1,
            "name": "macbook pro",
            "code": "001",
            "price": 3242222
        },
        "amount": 456000,
        "price": 545555
    }
    */
