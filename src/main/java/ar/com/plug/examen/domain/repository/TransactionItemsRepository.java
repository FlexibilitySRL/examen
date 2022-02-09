package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionItemsRepository extends JpaRepository<TransactionItem,Long> {
}
