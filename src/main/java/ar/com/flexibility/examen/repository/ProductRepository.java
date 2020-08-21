package ar.com.flexibility.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Query(value = "SELECT * FROM PRODUCT WHERE ID = ?1", nativeQuery = true)
	Product findById(Long id);

}
