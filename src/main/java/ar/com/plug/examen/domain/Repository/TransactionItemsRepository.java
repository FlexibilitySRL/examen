package ar.com.plug.examen.domain.Repository;

import ar.com.plug.examen.domain.model.TransactionItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemsRepository extends JpaRepository<TransactionItems, Long>, JpaSpecificationExecutor<TransactionItems> {

}
