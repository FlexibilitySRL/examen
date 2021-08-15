package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.PaymentType;
import ar.com.plug.examen.domain.entity.Transaction;
import ar.com.plug.examen.domain.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionRepository
 * Description:            Interface that handles access to the DB for the Transaction entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>
{
    List<Transaction> getAllByPaymentType( PaymentType paymentType );
    List<Transaction> getAllByTransactionStatus( TransactionStatus transactionStatus );
}