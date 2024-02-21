package ar.com.plug.examen.infrastructure.db.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.ProductEntity;

public interface ProductEntityRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByBarCode(String barCode);

}
