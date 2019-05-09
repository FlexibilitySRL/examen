package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.exception.GenericProductException;
import ar.com.flexibility.examen.domain.model.Product;
import javassist.NotFoundException;

import java.util.List;

public interface ProductService 
{

    String FIND_ALL_OK = "Obtención de Lista de Productos exitosa.";
    String FIND_ONE_OK ="Obtención del Producto éxitosa";

    String ADD_CODE_OK = "Producto creado con éxito";
    String UPDATE_CODE_OK = "Producto actualizado con éxito";
    String DELETE_CODE_OK = "Producto eliminado con éxito";


    void deleteAll();
    List<Product> findAll();
    Product findOne(Long id) throws NotFoundException, IllegalArgumentException;

    Product add(Product product) throws GenericProductException;
    Product update(Product product) throws NotFoundException, GenericProductException;
    void delete(Long id) throws NotFoundException;


}
