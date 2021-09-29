/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.entity.ClientProduct;
import ar.com.plug.examen.repository.ClientProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProductService {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ClientProductRepository repository;
    
    public List<ClientProduct> getClientsProducts(){
    
       try{
            logger.info("Servicio consultar compras ejecutado OK"); 
            return repository.findAll();
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar compras: "+e);
            return repository.findAll();
        }    
    
    }
    
    public ClientProduct updateClientProduct(ClientProduct clientProduct){
        
        ClientProduct existingClient = repository.findById(clientProduct.getId()).orElse(null);
        existingClient.setEstado(clientProduct.getEstado());
        
    try{
            logger.info("Servicio aprobar compra ejecutado OK");
            return repository.save(existingClient);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio aprobar compra: "+e);
            return repository.save(existingClient);
        }    
    
    }
    
}
