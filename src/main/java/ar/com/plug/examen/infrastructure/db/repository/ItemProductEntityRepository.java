package ar.com.plug.examen.infrastructure.db.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.ItemProductEntity;

public interface ItemProductEntityRepository extends CrudRepository<ItemProductEntity, Integer> {

}
