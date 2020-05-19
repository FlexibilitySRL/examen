package ar.com.flexibility.examen.model.repository;

import ar.com.flexibility.examen.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
