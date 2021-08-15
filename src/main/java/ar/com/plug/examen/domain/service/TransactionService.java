package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.entity.PaymentType;
import ar.com.plug.examen.domain.entity.TransactionStatus;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    TransactionService
 * Description:             Interface for handling service layer of Transaction's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface TransactionService
{
    List<TransactionDTO> findAll();
    List<TransactionDTO> findAllByPaymentType( PaymentType paymentType );
    List<TransactionDTO> findAllByTransactionStatus( TransactionStatus transactionStatus );
}
