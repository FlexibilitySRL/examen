package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.flexibility.examen.app.api.AuthorizationApi;
import ar.com.flexibility.examen.app.api.PurchaseItemRequestApi;
import ar.com.flexibility.examen.app.api.PurchaseRequestApi;
import ar.com.flexibility.examen.app.api.PurchaseResponseApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;
import ar.com.flexibility.examen.domain.repositories.IProductRepository;
import ar.com.flexibility.examen.domain.service.IPurchaseService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {
	
	@Autowired
	private IProductRepository repository;
	
	@Autowired
	IPurchaseService service;
	
	final String ERROR_MESSAJE = "Internal server error, see the log for more details";
	
	private static Logger logger = LogManager.getLogger(ProductController.class);
	
	final String CONTROLER_NAME = this.getClass().getSimpleName();
	final String entityName     = CONTROLER_NAME.replace("Controller", "");
	final String entityApiName  = entityName+"Api";

	@PostMapping
	@ApiOperation(value = "add", notes = "Add a new Purchase Order", response = PurchaseRequestApi.class)
	public ResponseEntity<PurchaseResponseApi> add(@RequestBody PurchaseRequestApi purchaseRequestApi) throws Exception
    {
	
		String methodName = "add";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));				
		
		ResponseEntity<PurchaseResponseApi> responseEntity;
        
        try {
        	
        	Purchase purchase = new Purchase();
    		
    		purchase.setProvider(purchaseRequestApi.getProvider());
    		
    		for(PurchaseItemRequestApi item : purchaseRequestApi.getItems()){
    			
    			Product product = repository.findOne(item.getIdProducto());
    			
    			if(product != null){
    				
    				PurchaseItem purchaseItem = new PurchaseItem(product, item.getQuantity(), item.getPrice());
    				
    				purchaseItem.setPurchase(purchase);
    				purchase.getItems().add(purchaseItem);
    			}
    		}
    		
    		purchase = service.add(purchase);
    		
    		responseEntity = ResponseEntity.ok().body(new PurchaseResponseApi(purchase));

        } catch (Exception e) {
        	
        	logger.error(e.getMessage());
        	
        	throw new Exception(ERROR_MESSAJE);
        }
        
        logger.info(String.format("End the method of %s %s", methodName, entityName ));

        return responseEntity;
    }

	
	@GetMapping
	@ApiOperation(value = "getAll", notes = "Get the list of all Purchase Orders", response = PurchaseResponseApi.class)
	public ResponseEntity<List<PurchaseResponseApi>> getAll() throws Exception
	{

		String methodName = "getAll";
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<List<PurchaseResponseApi>> responseEntity;
		
		try {					
			
			Iterable<Purchase> lista    = service.getAll();
			Iterator<Purchase> iterator = lista.iterator();
			
			List<PurchaseResponseApi>  entityList = new ArrayList<PurchaseResponseApi>();
			
			while(iterator.hasNext()){
				Purchase purchase = iterator.next();
				entityList.add(new PurchaseResponseApi(purchase));
			}
			
			logger.info(String.format("Map %s to %s", entityName, entityApiName));
			
			responseEntity = ResponseEntity.ok().body(entityList);
			
		} catch (Exception e) {	
			
			logger.error(e.getMessage());
			
			throw new Exception(ERROR_MESSAJE);
		}
		
		logger.info(String.format("End the method of %s %s", methodName, entityName ));
		
		return responseEntity;
	}
	
	@GetMapping(path="/{status}")
	@ApiOperation(value = "getAll", notes = "Get the list of Purchase Orders by status", response = PurchaseResponseApi.class)
	public ResponseEntity<List<PurchaseResponseApi>> getByStatus(@PathVariable("status") String status) throws Exception
	{

		String methodName = "getAll";
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<List<PurchaseResponseApi>> responseEntity;
		
		try {					
			
			Iterable<Purchase> lista    = service.getByStatus(status.toUpperCase());
			Iterator<Purchase> iterator = lista.iterator();
			
			List<PurchaseResponseApi>  entityList = new ArrayList<PurchaseResponseApi>();
			
			while(iterator.hasNext()){
				Purchase purchase = iterator.next();
				entityList.add(new PurchaseResponseApi(purchase));
			}
			
			logger.info(String.format("Map %s to %s", entityName, entityApiName));
			
			responseEntity = ResponseEntity.ok().body(entityList);
			
		} catch (Exception e) {	
			
			logger.error(e.getMessage());
			
			throw new Exception(ERROR_MESSAJE);
		}
		
		logger.info(String.format("End the method of %s %s", methodName, entityName ));
		
		return responseEntity;
	}
	
	@PutMapping(path="/pending")
	@ApiOperation(value = "add", notes = "Authorize or reject the purchase order", response = AuthorizationApi.class)
	public ResponseEntity<PurchaseResponseApi> check(@RequestBody AuthorizationApi authorizationApi) throws Exception
    {
	
		String methodName = "check";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));				
		
		ResponseEntity<PurchaseResponseApi> responseEntity;
        
        try {
        	
        	Purchase purchase =  service.checkPurchase(authorizationApi.getIdPurchase(), 
        			                                   authorizationApi.getStatus().toUpperCase(), 
        			                                   authorizationApi.getReviewer(), 
        			                                   authorizationApi.getObservations()); 
        	
        	responseEntity = ResponseEntity.ok().body(new PurchaseResponseApi(purchase));

        } catch (Exception e) {
        	
        	logger.error(e.getMessage());
        	
        	throw new Exception(ERROR_MESSAJE);
        }
        
        logger.info(String.format("End the method of %s %s", methodName, entityName ));

        return responseEntity;
    }
}
