package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.TransactionStatusDTO;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    TransactionStatusService
 * Description:             Interface for handling service layer of TransactionStatus's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface TransactionStatusService
{
    List<TransactionStatusDTO> findAll();
}