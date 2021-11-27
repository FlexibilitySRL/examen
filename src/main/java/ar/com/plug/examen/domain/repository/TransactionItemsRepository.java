package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.TransactionItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionItemsRepository extends JpaRepository<TransactionItems, Long>{
    
}
