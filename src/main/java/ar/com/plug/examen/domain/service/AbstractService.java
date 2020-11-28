/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.BaseEntity;
import java.util.Set;

/**
 *
 * @author msulbara
 */
public interface AbstractService<T extends BaseEntity, ID extends Long> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);
    
    T update(T object);

    void deleteById(ID id);

    void delete(T object);
}
