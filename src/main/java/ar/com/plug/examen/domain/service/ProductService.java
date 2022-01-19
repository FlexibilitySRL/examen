package ar.com.plug.examen.domain.service;

import java.util.ArrayList;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    
    public ArrayList<Product> obtenerProducts(){
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product crearProduct(Product product){
        return productRepository.save(product);
    }

    public Product modificarProduct(Product product){
        return productRepository.save(product);
    }

    public boolean eliminarProduct(Long id) {
        try{
            productRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}