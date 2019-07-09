package ar.com.flexibility.examen.domain.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}