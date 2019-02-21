package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findBySeller(Seller seller);

}
