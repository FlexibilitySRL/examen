package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.purchaseTransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface purchaseTransactionRepository extends CrudRepository<purchaseTransactionModel, Integer> {

}
