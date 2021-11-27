package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    Optional<Product> findByIdAndStockGreaterThanEqual(Long id, Long quantity);
    
}
