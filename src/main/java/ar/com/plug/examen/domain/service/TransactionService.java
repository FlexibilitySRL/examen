package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import java.util.List;


public interface TransactionService {
    
    /**
   * List all transactions
   * @return List<TransactionApi>
   */
  List<TransactionApi> findAll();

  /**
   * Find transaction by id
   * @param id
   * @return TransactionApi
   */
  TransactionApi findByIdChecked(Long id);

  /**
   * Create a transaction
   * @param transactionApiRequest
   * @return TransactionApi
   */
  TransactionApi save(TransactionApiRequest transactionApiRequest);

  /**
   * Approve transaction by id
   * @param id
   * @param status
   * @return TransactionApi
   */
  TransactionApi updateStatus(Long id, TransactionStatusEnum status);
  
}
