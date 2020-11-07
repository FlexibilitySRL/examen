package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Products;
import ar.com.plug.examen.service.ProductService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Contains methods for Products.
 *
 * @author Camilo Villate
 */
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

    /**
     * Create a new Product in the database
     *
     * @param product - Json model of the product
     * @return - response
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewProduct(@RequestBody Products product){
        int result = productService.insertProduct(product);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Retrieve list of products from database
     *
     * @return - response Json Array whit all products
     */
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Products> fetchProducts(){
        return productService.getAllProducts();
    }

    /**
     * Update Product in the database
     *
     * @param product - Json model of the Product
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateProduct(@RequestBody Products product){
        int result = productService.updateProduc(product);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Delete Product from the database
     *
     * @param id - id to be delete
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        int result = productService.deleteProduct(id);
        return MessageResponse.getIntegerResponseEntity(result);
    }

}
