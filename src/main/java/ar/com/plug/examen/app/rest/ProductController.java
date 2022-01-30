package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.validators.Validator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;


    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO)
    {
        validator.validateProductDTO(productDTO, Boolean.FALSE);
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllCProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "API to get product by id", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@PathVariable Long id)
    {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO)
    {
        validator.validateProductDTO(productDTO, Boolean.TRUE);
        return new ResponseEntity<>(productService.update(productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)
    {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
