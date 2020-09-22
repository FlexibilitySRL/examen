package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
   
	public Product findByCode(String document);
	
	public List<Product> findByIdIn(List<Long> ids);
	
}