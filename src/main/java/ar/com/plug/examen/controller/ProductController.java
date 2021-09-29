/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.plug.examen.controller;

import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.entity.Product;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ALEJANDRO
 */
@RestController
public class ProductController {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ProductService service;
    
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        logger.info("Servicio agregar producto");
        return service.saveProduct(product);    
    }
    
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        logger.info("Servicio agregar productos");
        return service.saveProducts(products);
    
    }
    
    @GetMapping("/products")
    public List<Product> findAllProducts(){
        logger.info("Servicio consultar todos los productos");
        return service.getProducts();
    
    }
    
    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id){
        logger.info("Servicio consultar producto por Id");
        return service.getProductById(id);
    
    }
    
    @GetMapping("/productByName/{name}")
    public Product findProductByName(@PathVariable String name){
        logger.info("Servicio consultar producto por Nombre");
        return service.getProductByName(name);
    
    }
    
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        logger.info("Servicio Actualizar producto");
        return service.updateProduct(product);
    
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        logger.info("Servicio eliminar producto");
        return service.deleteProduct(id);
        
    } 
    
}
