package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
