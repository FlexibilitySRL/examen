package ar.com.plug.examen.domain.crud;

import ar.com.plug.examen.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionCrudRepository extends CrudRepository<Transaction,Long> {

    Optional<List<Transaction>> findByStateEquals(String state);

}
