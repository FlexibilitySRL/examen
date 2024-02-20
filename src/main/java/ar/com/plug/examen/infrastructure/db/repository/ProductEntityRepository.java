package ar.com.plug.examen.infrastructure.db.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.ProductEntity;

public interface ProductEntityRepository extends CrudRepository<ProductEntity, String> {

}
