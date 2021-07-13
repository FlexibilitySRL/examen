package ar.com.plug.examen.interfaces;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Product;

public interface IProduct extends CrudRepository<Product,Integer>{

}
