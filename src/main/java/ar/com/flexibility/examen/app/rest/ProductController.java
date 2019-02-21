/**
 * 
 */
package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author ro
 *
 */
@RestController
@RequestMapping(path = "/api/product")
@Api(tags="product")
public class ProductController {
	
    @Autowired
    private ProductService productService;
    
    @Autowired
    private SellerService sellerService;
    
    @GetMapping
    public ResponseEntity<List<ProductApi>> getAllProducts(){
    	return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }
    
    @GetMapping("/{idSeller}")
    @ApiOperation(value= "All products of seller", notes = "Service to list of product of seller")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "OK"),
    		@ApiResponse(code= 400, message="Seller not found")})
    public ResponseEntity<List<ProductApi>> getAllProductsOfSeller(@PathVariable("idSeller") Long idSeller){
    	Seller seller = this.sellerService.findById(idSeller);
    	if(seller == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(productService.findBySeller(seller),HttpStatus.OK);
    }
    
    

}
