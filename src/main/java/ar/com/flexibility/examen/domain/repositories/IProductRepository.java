package ar.com.flexibility.examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
	
}
