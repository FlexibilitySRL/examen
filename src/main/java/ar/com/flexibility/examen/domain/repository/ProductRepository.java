package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Product;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {

}
