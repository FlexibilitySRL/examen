/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author msulbara
 */
public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
