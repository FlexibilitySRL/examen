package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findById(Long id);
	
}
