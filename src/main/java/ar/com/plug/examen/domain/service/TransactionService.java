package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRquest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import java.util.List;

public interface TransactionService {

  /**
   * Get all transactions
   * @return TransactionApi
   */
  List<TransactionApi> findAll();

  /**
   * Get transaction by id
   * @param id
   * @return TransactionApi
   * @throws GenericNotFoundException
   */
  TransactionApi findByIdChecked(Long id) throws GenericNotFoundException;

  /**
   * Create a transaction
   * @param transactionApi
   * @return TransactionApi
   * @throws GenericBadRequestException
   */
  TransactionApi save(TransactionApiRquest transactionApi) throws GenericBadRequestException;

  /**
   * Approve transaction by id
   * @param id
   * @param status
   * @return TransactionApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  TransactionApi updateStatus(Long id, TransactionStatusEnum status) throws GenericNotFoundException, GenericBadRequestException;

}
