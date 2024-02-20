package ar.com.plug.examen.infrastructure.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, String> {
    List<TransactionEntity> findByApproved(boolean approved);
}
