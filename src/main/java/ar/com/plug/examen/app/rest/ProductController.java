package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.config.SwaggerResponseOk;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path="/product")
public class ProductController {

    @Autowired
    private  ProductService productService;

    @PostMapping(path = "/saveProduct",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @DeleteMapping(path = "/removeProduct/{idProduct}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable("idProduct") Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping(path = "/editProduct/{idProduct}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifyProduct(@PathVariable("idProduct") Long id, @RequestBody ProductDTO productDTO) {
        productService.editProduct(id, productDTO);
    }

}
