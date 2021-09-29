/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.repository;

import ar.com.plug.examen.entity.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ALEJANDRO
 */
public interface ClientProductRepository extends JpaRepository<ClientProduct, Integer> {
    
    
    
}
