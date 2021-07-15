package ar.com.plug.examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
