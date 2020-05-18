package ar.com.flexibility.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
	
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Buscar compras", response = Purchase.class)
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllPurchases(){
    	LOGGER.info("Servicio de Busqueda de todas las compras");
        return purchaseService.getPurchases();
    }
    
    @ApiOperation(value = "Buscar una compra", response = Purchase.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPurchase(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de Busqueda de la compra con Id: {}", id);
    	return purchaseService.getPurchaseById(id);
    }
    
    @ApiOperation(value = "iniciar una compra", response = Purchase.class)
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> insertNewPurchase(@RequestBody Purchase purchase){
    	LOGGER.info("Servicio de Iniciacion de una compra");
    	return purchaseService.insertPurchase(purchase);
    }

    @ApiOperation(value = "Aprovar compra", response = Purchase.class)
    @PutMapping(value = "/{id}/approve", produces = "application/json")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") Long id){
    	LOGGER.info("Servicio de aprovacion de la compra con id: {}", id);
        return purchaseService.approvePurchase(id);
    }
    
}
