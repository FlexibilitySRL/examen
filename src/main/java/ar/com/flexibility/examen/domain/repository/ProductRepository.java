package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
