package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}