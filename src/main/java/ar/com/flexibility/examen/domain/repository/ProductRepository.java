package ar.com.flexibility.examen.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Product findByName(String name);
	
	public Optional<Product> findById(Integer productId);
}
