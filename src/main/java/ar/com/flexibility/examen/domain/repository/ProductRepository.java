package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>{
}
