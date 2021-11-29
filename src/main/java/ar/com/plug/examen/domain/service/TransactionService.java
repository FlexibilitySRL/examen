package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.dto.TransactionDto;
import ar.com.plug.examen.app.dto.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import java.util.List;


public interface TransactionService {
    
    /**
   * List all transactions
   * @return List<TransactionApi>
   */
  List<TransactionDto> findAll();

  /**
   * Find transaction by id
   * @param id
   * @return TransactionApi
   */
  TransactionDto findByIdChecked(Long id);

  /**
   * Create a transaction
   * @param transactionApiRequest
   * @return TransactionApi
   */
  TransactionDto save(TransactionApiRequest transactionApiRequest);

  /**
   * Approve transaction by id
   * @param id
   * @param status
   * @return TransactionApi
   */
  TransactionDto updateStatus(Long id, TransactionStatusEnum status);
  
}
