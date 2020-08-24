package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.PurchasesApi;
import ar.com.flexibility.examen.domain.service.ProcessPurchasesService;

@RestController
@RequestMapping(path = "/purchases")
public class PurchasesController {
	
	private final Logger log = LoggerFactory.getLogger(PurchasesController.class);

	@Autowired
	private ProcessPurchasesService purchaseService;

	/**
	 * Purchase approval operation
	 * @param purchaseApi
	 * @return
	 */
	@PostMapping("/approve")
	public ResponseEntity<?> approve(@RequestBody PurchasesApi purchaseApi) {
		String mensaje = "";
		try {
			log.info("Approving a purchase...");
			return new ResponseEntity<PurchasesApi>(purchaseService.create(purchaseApi), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error creating a purchase.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Purchase search operation
	 * @return
	 */
	@GetMapping("/search")
	public ResponseEntity<?> search() {
		String mensaje = "";
		try {
			log.info("Searching purchases...");
			return new ResponseEntity<List<PurchasesApi>>(purchaseService.search(), HttpStatus.OK);
		} catch (Exception e) {
			mensaje = "There was an error creating a purchase.";
			log.error(mensaje, e.getMessage());
			return new ResponseEntity<String>(mensaje + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
