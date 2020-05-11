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
import ar.com.flexibility.examen.app.api.CustomerApi;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.IProcessGenericEntityService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
	@Autowired
	IProcessGenericEntityService<Customer> service;
	
	@Autowired
    private ModelMapper modelMapper;
	
	final String ERROR_MESSAJE = "Internal server error, see the log for more details";
	
	private static Logger logger = LogManager.getLogger(ProductController.class);
	
	final String CONTROLER_NAME = this.getClass().getSimpleName();
	final String entityName     = CONTROLER_NAME.replace("Controller", "");
	final String entityApiName  = entityName+"Api";
	
	@PostMapping
	@ApiOperation(value = "add", notes = "Add a new customer", response = CustomerApi.class)
    public ResponseEntity<CustomerApi> add(@RequestBody CustomerApi customerApi) throws Exception
    {
	
		String methodName = "add";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));				
		
        ResponseEntity<CustomerApi> responseEntity;
        
        try {
        	
        	Customer customer = modelMapper.map(customerApi, Customer.class);
        	
        	logger.info(String.format("Map %s to %s", entityApiName, entityName));

        	service.add(customer);
        	
        	customerApi = modelMapper.map(customer, CustomerApi.class);
        	
        	logger.info(String.format("Map %s to %s", entityName, entityApiName));

            responseEntity = ResponseEntity.ok().body(customerApi);

        } catch (Exception e) {
        	
        	logger.error(e.getMessage());
        	
        	throw new Exception(ERROR_MESSAJE);
        }
        
        logger.info(String.format("End the method of %s %s", methodName, entityName ));

        return responseEntity;
    }
	
	@GetMapping
	@ApiOperation(value = "getAll", notes = "Get the list of all customer", response = CustomerApi.class)
	public ResponseEntity<List<CustomerApi>> getAll() throws Exception
	{

		String methodName = "getAll";
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<List<CustomerApi>> responseEntity;
		
		try {					
			
			Iterable<Customer> lista    = service.getAll();
			Iterator<Customer> iterator = lista.iterator();
			
			List<CustomerApi>  entityList = new ArrayList<CustomerApi>();
			
			while(iterator.hasNext()){
				Customer customer = iterator.next();
				entityList.add(modelMapper.map(customer, CustomerApi.class));
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
	@ApiOperation(value = "Update", notes = "update customer data", response = CustomerApi.class)
	public ResponseEntity<CustomerApi> update(@RequestBody CustomerApi customerApi) throws Exception
	{
		String methodName = "getAll";
		
		logger.info(String.format("Start the method of %s %s", methodName, entityName ));
		
		ResponseEntity<CustomerApi> responseEntity;
		
		try {		
			
			Customer customer = modelMapper.map(customerApi, Customer.class);
			
			logger.info(String.format("Map %s to %s", entityApiName, entityName));
			
			customer = service.update(customer);
			
			customerApi = modelMapper.map(customer, CustomerApi.class);
			
			logger.info(String.format("Map %s to %s", entityName, entityApiName));

            responseEntity = ResponseEntity.ok().body(customerApi);
			
		} catch (Exception e) {
			throw new Exception(ERROR_MESSAJE);
		}
		
		logger.info(String.format("End the method of %s %s", methodName, entityName ));
		
		return responseEntity;
	}
	
	@DeleteMapping
	@ApiOperation(value = "delete", notes = "Delete a customer by id", response = CustomerApi.class)
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
