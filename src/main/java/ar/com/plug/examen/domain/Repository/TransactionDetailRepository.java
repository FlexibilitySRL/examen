package ar.com.plug.examen.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>, JpaSpecificationExecutor<TransactionDetail> {

}
