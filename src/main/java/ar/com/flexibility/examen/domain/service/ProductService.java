package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Product;
import javassist.NotFoundException;

import java.util.List;

public interface ProductService 
{
    void deleteAll() throws GenericException;
    
    List<Product> findAll() throws GenericException;
    Product findOne(Long id) throws NotFoundException, GenericException;

    Product add(Product product) throws GenericException;
    Product update(Product product) throws NotFoundException, GenericException;
    void delete(Long id) throws NotFoundException, GenericException;
}
