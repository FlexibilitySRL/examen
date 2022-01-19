package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public ArrayList<Product> obtenerProducts(){
        return productService.obtenerProducts();
    }

    @PostMapping()
    public Product crearProduct(@RequestBody Product product){
        return this.productService.crearProduct(product);
    }

    @PutMapping()
    public Product modificarProduct(@RequestBody Product product){
        return this.productService.modificarProduct(product);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.productService.eliminarProduct(id);
        if (ok){
            return "Se eliminó el producto con id " + id;
        }else{
            return "No pudo eliminar el producto con id" + id;
        }
    }

}