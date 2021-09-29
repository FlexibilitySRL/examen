/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.controller;

import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.entity.Client;
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
public class ClientController {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ClientService service;
    
    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client){
        logger.info("Servicio agregar Cliente");
        return service.saveClient(client);
    
    }
    
    @PostMapping("/addClients")
    public List<Client> addClients(@RequestBody List<Client> clients){
        logger.info("Servicio agregar Clientes");
        return service.saveClients(clients);
    
    }
    
    @GetMapping("/clients")
    public List<Client> findAllClients(){
        logger.info("Servicio consultar Clientes");
        return service.getClients();
    
    }
    
    @GetMapping("/clientById/{id}")
    public Client findClientById(@PathVariable int id){
        logger.info("Servicio consultar Clientes por Id");
        return service.getClientById(id);
    
    }    
    
    @GetMapping("/clientByName/{name}")
    public Client findProductByName(@PathVariable String name){
        logger.info("Servicio consultar Clientes por Nombre");
        return service.getClientByName(name);
    
    }
    
    @PutMapping("/updateClient")
    public Client updateClient(@RequestBody Client client){
        logger.info("Servicio actualizar Cliente");
        return service.updateClient(client);
    
    }
    
    @DeleteMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable int id){
        logger.info("Servicio eliminar Cliente");
        return service.deleteClient(id);
        
    } 
    
}
