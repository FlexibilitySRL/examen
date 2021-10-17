package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("save")
    @ApiOperation("Registrar un nuevo producto")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @PutMapping("update")
    @ApiOperation("Actualizar un  producto")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Product> update(@RequestBody Product product){

        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation("Buscar producto por Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),

    })
    public ResponseEntity<Product> getProductById(
            @ApiParam(value = "Id del producto a consultar", required = true, example = "2")
            @PathVariable("id") long productId){

        return productService.getById(productId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/stockgreaterthan/{stock}")
    @ApiOperation("Buscar productos cuyo stock es mayor que")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),

    })
    public ResponseEntity<List<Product>> findByStokGreatherThan(
            @ApiParam(value = "stock a consultar", required = true, example = "20")
            @PathVariable("stock") int stock) {

        return productService.findByStokGreatherThan(stock)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/pricelessthan/{price}")
    @ApiOperation("Buscar productos cuyo precio es menor que")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<Product>> findByPriceIsLessthan(
            @ApiParam(value = "precio por el que se desea consultar", required = true, example = "150000")
            @PathVariable("price") long price) {

        return productService.findByPriceIsLessthan(price)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Buscar todos los productos")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> findAllProducts(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }


}
