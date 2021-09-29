/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.controller.ProductController;
import ar.com.plug.examen.entity.Product;
import ar.com.plug.examen.repository.ProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository repository;
    
    
    public Product saveProduct(Product product){
     
        try{
            logger.info("Servicio agregar producto ejecutado OK");
            return repository.save(product);
        }catch(Exception e){
            logger.error("Error al ejecutar el servicio agregar producto: "+e);
            return repository.save(product);
        }   
    }
    
    public List<Product> saveProducts(List<Product> products){
    
        try{
            logger.info("Servicio agregar productos ejecutado OK"); 
            return repository.saveAll(products);
           }catch(Exception e){
            logger.error("Error al ejecutar el servicio agregar productos: "+e);
            return repository.saveAll(products);
        }
    
    }
    
    public List<Product> getProducts(){
    
       try{
            logger.info("Servicio consultar productos ejecutado OK"); 
            return repository.findAll();
          }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar productos: "+e);
            return repository.findAll();
        }
    
    }
    
    public Product getProductById(int id){
    
      try{
            logger.info("Servicio consultar producto por Id ejecutado OK");   
            return repository.findById(id).orElse(null);
          }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar producto por Id: "+e);
            return repository.findById(id).orElse(null);
        } 
    }
    
    public Product getProductByName(String name){
    
       try{
            logger.info("Servicio consultar producto por Nombre ejecutado OK"); 
            return repository.findByName(name);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio consultar producto por Nombre: "+e);
            return repository.findByName(name);
        } 
    
    }
    
    public String deleteProduct(int id){
    
      try{
            logger.info("Servicio eliminar producto ejecutado OK");   
            repository.deleteById(id);     
            return "Producto Eliminado";    
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio eliminar producto: "+e);
            return "Producto No Eliminado";
        } 
    }
    
    public Product updateProduct(Product product){
        
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
    
       try{
            logger.info("Servicio actualizar producto ejecutado OK"); 
            return repository.save(existingProduct);
            }catch(Exception e){
            logger.error("Error al ejecutar el servicio actualizar producto: "+e);
            return repository.save(existingProduct);
        } 
    }
    
}
