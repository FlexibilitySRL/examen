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

import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.SellerService;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {
	
	@Autowired
	private Mapper mapper;
    
  
    private final SellerService sellerService;

    @Autowired
    public SellerController (SellerService sellerService) {
        this.sellerService = sellerService;
 
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create successfully."),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> createSeller(@RequestBody SellerDto dto)
    {
    	Seller sellerNew = mapper.map(dto, Seller.class);

    	return new ResponseEntity<>(sellerService.create(sellerNew), HttpStatus.OK);
    }

    
    @GetMapping(path = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<List<Seller>> getSellersAll() {
    	
    	List<Seller> sellers = sellerService.getSellers();
        
        return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
    }
    
    
    
	@GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
            @ApiResponse(code = 404, message = "Not Found.")
    })
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
    	
    	Optional<Seller> seller = sellerService.getSellerById(id);

        if (!seller.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Seller>(seller.get(), HttpStatus.OK);
    }
    

	
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request updated successfully."),
    })
    public ResponseEntity<Seller> updateSellerById(@PathVariable("id") Long id,@RequestBody ProductDto dto) {
    	
    	
    	Seller productToUpdate= mapper.map(dto, Seller.class);
 

    	return new ResponseEntity<>(sellerService.update(id,productToUpdate), HttpStatus.OK);
 
     }
    
    
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successfully."),
    })
    public ResponseEntity<Seller> deleteSellerById(@PathVariable("id") Long id) {
    	
    	sellerService.delete(id);
 
        return new ResponseEntity<Seller>( HttpStatus.OK);
    }
    
    
}
