package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.productModel;
import ar.com.plug.examen.domain.model.purchaseTransactionModel;
import ar.com.plug.examen.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAll")
    @ApiOperation("Get all products")
    @ApiResponse(code = 200, message = "OK")
    private ResponseEntity<List<productModel>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/save")
    @ApiOperation("Save a product")
    public ResponseEntity<productModel> saveProduct(@RequestBody productModel productModel){

        try
        {
            productModel productSaved = productService.saveProduct(productModel);
            return ResponseEntity.created(new URI("/products/"+productSaved.getId())).body(productSaved);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a product")
    private ResponseEntity<productModel> update(@PathVariable("id") Integer id, @RequestBody productModel productModel)
    {
        try
        {
            productModel productUpdated = productService.updateProduct(id,productModel);
            return ResponseEntity.created(new URI("/products/"+productUpdated.getId())).body(productUpdated);
        }
        catch (Exception e)
        {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete a product by Id")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        boolean ok = this.productService.deleteProduct(id);

        if (ok) return ResponseEntity.ok("Se eliminó el producto con id " + id);
        else return ResponseEntity.badRequest().body("No se pudo eliminar el producto con id" + id);
    }

}
