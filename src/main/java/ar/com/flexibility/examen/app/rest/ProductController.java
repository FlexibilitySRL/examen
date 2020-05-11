package ar.com.flexibility.examen.app.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.IProcessGenericEntityService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	
	@Autowired
	IProcessGenericEntityService<Product> service;
	
	@Autowired
    private ModelMapper modelMapper;
	
	final String ERROR_MESSAJE = "Internal server error, see the log for more details";
	
	private static Logger logger = LogManager.getLogger(ProductController.class);
	
	final String CONTROLER_NAME = this.getClass().getSimpleName();
	final String entityName     = CONTROLER_NAME.replace("Controller", "");
	final String entityApiName  = entityName+"Api";
	
	@PostMapping
	@ApiOperation(value = "add", notes = "Add a new Product", response = ProductApi.class)
    public ResponseEntity<ProductApi> add(@RequestBody ProductApi productApi) throws Exception
    {
	
		String methodName = "add";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));				
		
        ResponseEntity<ProductApi> responseEntity;
        
        try {
        	
        	Product product = modelMapper.map(productApi, Product.class);
        	
        	logger.info(String.format("Map %s to %s", entityApiName, entityName));

        	service.add(product);
        	
        	productApi = modelMapper.map(product, ProductApi.class);
        	
        	logger.info(String.format("Map %s to %s", entityName, entityApiName));

            responseEntity = ResponseEntity.ok().body(productApi);

        } catch (Exception e) {
        	
        	logger.error(e.getMessage());
        	
        	throw new Exception(ERROR_MESSAJE);
        }
        
        logger.info(String.format("End the method of %s %s", methodName, entityName ));

        return responseEntity;
    }
	
	@GetMapping
	@ApiOperation(value = "getAll", notes = "Get the list of all products", response = ProductApi.class)
	public ResponseEntity<List<ProductApi>> getAll() throws Exception
	{

		String methodName = "getAll";
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<List<ProductApi>> responseEntity;
		
		try {					
			
			Iterable<Product> lista    = service.getAll();
			Iterator<Product> iterator = lista.iterator();
			
			List<ProductApi>  entityList = new ArrayList<ProductApi>();
			
			while(iterator.hasNext()){
				Product product = iterator.next();
				entityList.add(modelMapper.map(product, ProductApi.class));
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
	
	@PutMapping
	@ApiOperation(value = "Update", notes = "update product data", response = ProductApi.class)
	public ResponseEntity<ProductApi> update(@RequestBody ProductApi productApi) throws Exception
	{
		String methodName = "getAll";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<ProductApi> responseEntity;
		
		try {		
			
			Product product = modelMapper.map(productApi, Product.class);
			
			logger.info(String.format("Map %s to %s", entityApiName, entityName));
			
			product = service.update(product);
			
			productApi = modelMapper.map(product, ProductApi.class);
			
			logger.info(String.format("Map %s to %s", entityName, entityApiName));

            responseEntity = ResponseEntity.ok().body(productApi);
			
		} catch (Exception e) {
			throw new Exception(ERROR_MESSAJE);
		}
		
		logger.info(String.format("End the method of %s %s", methodName, entityName ));
		
		return responseEntity;
	}
	
	@DeleteMapping
	@ApiOperation(value = "delete", notes = "Delete a product by id", response = ProductApi.class)
	public ResponseEntity<String> delete(@RequestBody long id) throws Exception
	{
		String methodName = "delete";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));

		ResponseEntity<String> responseEntity;
		
		try {		
						
			service.delete(id);
			
            responseEntity = ResponseEntity.ok().body("OK");
			
		} catch (Exception e) {
			throw new Exception(ERROR_MESSAJE);
		}
		
		logger.info(String.format("End the method of %s %s", methodName, entityName ));
		
		return responseEntity;
	}

}
