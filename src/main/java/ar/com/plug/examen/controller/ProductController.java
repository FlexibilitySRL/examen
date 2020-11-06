package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Products;
import ar.com.plug.examen.service.ProductService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/products"
)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewProduct(@RequestBody Products product){
        int result = productService.insertProduct(product);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Products> fetchProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateProduct(@RequestBody Products product){
        int result = productService.updateProduc(product);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        int result = productService.deleteProduct(id);
        return getIntegerResponseEntity(result);
    }


    private ResponseEntity<?> getIntegerResponseEntity(int result) {
        if(result ==1){
            return  ResponseEntity.ok().body(new MessageResponse("Proceso correcto"));
        }
        return ResponseEntity.badRequest().build();
    }
}
