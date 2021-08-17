package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
    Product findById(Long id);
}
