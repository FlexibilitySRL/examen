package ar.com.plug.examen.app.rest;

 

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.OrderDto;
import ar.com.plug.examen.app.api.RequestOrderUpdateStatus;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.service.SellerService;
@RestController
@RequestMapping(path = "/order")
public class OrderController {
	
	@Autowired
	private Mapper mapper;
    
  
    private final OrderService orderService;
    
    private final CustomerService customerService;
    
    private final SellerService sellerService;

    
    
    @Autowired
    public OrderController (OrderService orderService,
    						CustomerService customerService,
    					    SellerService sellerService
    						) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.sellerService = sellerService;
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create successfully."),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> createOrder(@RequestBody OrderDto dto)
    {
    	Order orderNew = mapper.map(dto, Order.class);

    	
    	Long idCustomer = dto.getIdCustomer();
    	Long idSeller = dto.getIdSeller();

    	Optional<Customer> resultCustomer = customerService.getCustomerById(idCustomer);
    	
    	if (!resultCustomer.isPresent()){
    		
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
   	
    	orderNew.setCustomer(resultCustomer.get());
    	
    	Optional<Seller> resultSeller = sellerService.getSellerById(idSeller);
    	
    	if (!resultSeller.isPresent()){
    		
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}

    	orderNew.setSeller(resultSeller.get());
    	
    	
    	
    	return new ResponseEntity<>(orderService.create(orderNew), HttpStatus.OK);
    }

    
    @GetMapping(path = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<List<Order>> getOrdersAll() {
    	
    	List<Order> orders = orderService.getOrders();
        
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    
    
    
	@GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
    	
    	Optional<Order> order = orderService.getOrderById(id);

        if (!order.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
    }
	
	
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request updated successfully."),
            @ApiResponse(code = 404, message = "Not Found.")

    })
    public ResponseEntity<Order> updateStatusOrderById(@PathVariable("id") Long id,@RequestBody RequestOrderUpdateStatus dto) {
    	
    	Optional<Order> optional = orderService.getOrderById(id);
    	
    	Order orderToUpdate = null;
    	
    	if(optional.isPresent()){
    		
    		orderToUpdate = optional.get();
    		orderToUpdate.setStatus(dto.getStatus());
    		
        	return new ResponseEntity<>(orderService.update(id,orderToUpdate), HttpStatus.OK);
    		
    	}else{
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
     }
    

    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
    })
    public ResponseEntity<Order> deleteOrderById(@PathVariable("id") Long id) {
    	
    	orderService.delete(id);
 
        return new ResponseEntity<Order>(HttpStatus.OK);
    }
    
    
}
