package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.app.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    public abstract ProductEntity findByProductCode(String productCode);
}
