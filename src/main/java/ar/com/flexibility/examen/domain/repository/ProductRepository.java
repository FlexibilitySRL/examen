package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
