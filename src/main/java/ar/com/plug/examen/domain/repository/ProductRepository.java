package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long > {

}
