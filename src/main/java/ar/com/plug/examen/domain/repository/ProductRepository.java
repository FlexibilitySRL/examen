package ar.com.plug.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findOneById(Long id);
	
	@Query(value = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Product> findByName(@Param("name") String name);

}