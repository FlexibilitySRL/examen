package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    public List<Product> findByName(String name);
}
