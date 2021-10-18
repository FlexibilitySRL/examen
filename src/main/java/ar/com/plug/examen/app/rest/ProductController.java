package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductDTO;
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

/**
 * Product Controller
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * Save a new Product
     * @param productDto
     * @return ResponseEntity<ProductDTO>
     */
    @PostMapping("save")
    @ApiOperation("Registrar un nuevo producto")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDto){

        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    /**
     * Update an existing product
     * @param productDto
     * @return ResponseEntity<ProductDTO>
     */
    @PutMapping("update")
    @ApiOperation("Actualizar un  producto")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDto){

        return new ResponseEntity<>(productService.update(productDto), HttpStatus.OK);
    }

    /**
     * Find a product given its Id
     * @param productId
     * @return ResponseEntity<ProductDTO>
     */
    @GetMapping("/{id}")
    @ApiOperation("Buscar producto por Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),

    })
    public ResponseEntity<ProductDTO> getProductById(
            @ApiParam(value = "Id del producto a consultar", required = true, example = "2")
            @PathVariable("id") long productId){

        return productService.getById(productId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     *  Find a list of products which stock is greater than
     * @param stock
     * @return ResponseEntity<List<ProductDTO>>
     */
    @GetMapping("/stockgreaterthan/{stock}")
    @ApiOperation("Buscar productos cuyo stock es mayor que")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),

    })
    public ResponseEntity<List<ProductDTO>> findByStokGreatherThan(
            @ApiParam(value = "stock a consultar", required = true, example = "20")
            @PathVariable("stock") int stock) {

        return productService.findByStokGreatherThan(stock)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Find a list of products which price is less than
     * @param price
     * @return ResponseEntity<List<ProductDTO>>
     */
    @GetMapping("/pricelessthan/{price}")
    @ApiOperation("Buscar productos cuyo precio es menor que")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<ProductDTO>> findByPriceIsLessthan(
            @ApiParam(value = "precio por el que se desea consultar", required = true, example = "150000")
            @PathVariable("price") long price) {

        return productService.findByPriceIsLessthan(price)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Find All products
     * @return ResponseEntity<List<ProductDTO>>
     */
    @GetMapping("/all")
    @ApiOperation("Buscar todos los productos")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<ProductDTO>> findAllProducts(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }


}
