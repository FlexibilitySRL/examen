/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.entity.Client;
import ar.com.plug.examen.repository.ClientRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ClientRepository repository;
    
    public Client saveClient(Client client){
    
        try{
            logger.info("Servicio agregar cliente ejecutado OK");
            return repository.save(client);
          }catch(Exception e){
            logger.error("Error al ejecutar el servicio agregar cliente: "+e);
            return repository.save(client);
        }    
    
    }
    
    public List<Client> saveClients(List<Client> clients){
    
       try{
            logger.info("Servicio agregar clientes ejecutado OK"); 
            return repository.saveAll(clients);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio agregar clientes: "+e);
            return repository.saveAll(clients);
        }  
    
    }
    
    public List<Client> getClients(){
    
       try{
            logger.info("Servicio consultar clientes ejecutado OK"); 
            return repository.findAll();
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar clientes: "+e);
            return repository.findAll();
        }  
    }
    
    public Client getClientById(int id){
    
       try{
            logger.info("Servicio consultar cliente por Id ejecutado OK"); 
            return repository.findById(id).orElse(null);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar cliente por Id: "+e);
            return repository.findById(id).orElse(null);
        }  
    }
        
    public Client getClientByName(String name){
     
        try{
            logger.info("Servicio consultar cliente por nombre ejecutado OK");
            return repository.findByName(name);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar cliente por nombre: "+e);
            return repository.findByName(name);
        }  
    
    }
    
    public String deleteClient(int id){
    
       try{
            logger.info("Servicio eliminar cliente ejecutado OK"); 
            repository.deleteById(id);     
            return "Cliente Eliminado"; 
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio eliminar cliente: "+e);
            return "Cliente No Eliminado";
        }  
    
    }
    
    public Client updateClient(Client client){
        
        Client existingClient = repository.findById(client.getId()).orElse(null);
        existingClient.setNumIdent(client.getNumIdent());
        existingClient.setName(client.getName());
        existingClient.setPhone(client.getPhone());
        existingClient.setAdress(client.getAdress());
    
       try{
            logger.info("Servicio actualizar cliente ejecutado OK"); 
            return repository.save(existingClient);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio actualizar cliente: "+e);
            return repository.save(existingClient);
        }  
    
    }
    
}
