package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.ProductDTO;
import ar.com.flexibility.examen.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity saveProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        productService.updateProduct(id, productDTO);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }
}
