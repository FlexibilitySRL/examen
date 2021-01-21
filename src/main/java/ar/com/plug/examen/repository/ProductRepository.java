package ar.com.plug.examen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
