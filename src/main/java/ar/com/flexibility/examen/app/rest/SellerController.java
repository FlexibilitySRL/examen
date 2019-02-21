package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

@RestController
@RequestMapping(path = "/api/seller")
@Api(tags="seller")
public class SellerController {
	
    @Autowired
    private ProductService productService;
    @Autowired
    private SellerService sellerService;
    
    @PostMapping("/{idSeller}")
    @ApiOperation(value= "Add new product", notes = "Service to add new products")
    @ApiResponses(value= {@ApiResponse(code= 200, message= "Product added successfully"),
    		@ApiResponse(code= 400, message="Seller not found")})
    public ResponseEntity<Product> addNewProduct(@PathVariable("idSeller") Long idSeller, ProductApi productApi) {
    	Seller seller = this.sellerService.findById(idSeller);
    	if(seller == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	Product product = new Product();
    	product.setName(productApi.getName());
    	product.setPrice(productApi.getPrice());
        return new ResponseEntity<>((product), HttpStatus.OK);
    }
    
    
    
}
