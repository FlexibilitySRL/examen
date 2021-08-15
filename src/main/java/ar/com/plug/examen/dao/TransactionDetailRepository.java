package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   TransactionDetailRepository
 * Description:            Interface that handles access to the DB for the TransactionDetail entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail,Long>
{}
