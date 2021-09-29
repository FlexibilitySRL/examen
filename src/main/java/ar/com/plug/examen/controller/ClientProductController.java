/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.controller;

import ar.com.plug.examen.domain.service.ClientProductService;
import ar.com.plug.examen.entity.ClientProduct;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientProductController {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ClientProductService service;
    
    @GetMapping("/buys")
    public List<ClientProduct> findAllBuys(){
        logger.info("Servicio buscar compras");
        return service.getClientsProducts();
    
    }
    
    @PutMapping("/aprobBuy")
    public ClientProduct updateProduct(@RequestBody ClientProduct clientProduct){
        logger.info("Servicio Aprobar compra");
        return service.updateClientProduct(clientProduct);
    
    }
    
}
