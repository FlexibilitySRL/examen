package ar.com.flexibility.examen.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.db.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
