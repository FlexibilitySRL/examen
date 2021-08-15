package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   TransactionStatusRepository
 * Description:            Interface that handles access to the DB for the TransactionStatus entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,Long>
{}