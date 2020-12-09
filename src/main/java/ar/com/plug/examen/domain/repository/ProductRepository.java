package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

	
}
