package ar.com.flexibility.examen.app.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.dto.PurchaseRequestDTO;
import ar.com.flexibility.examen.domain.service.PurchasesService;

@RestController
@RequestMapping(path = "/purchases")
public class PurchasesController {
	
	private static final Logger logger = LogManager.getLogger(PurchasesController.class);

	@Autowired
	private PurchasesService purchasesService;
	
	@PostMapping
	public ResponseEntity<Boolean> buy(@RequestBody PurchaseRequestDTO buys) throws Exception {
		if(purchasesService.buy(buys)) {	
			logger.error("Purchase invalid. {}", buys);
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Purchase created.");
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
