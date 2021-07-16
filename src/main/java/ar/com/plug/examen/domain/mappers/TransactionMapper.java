package ar.com.plug.examen.domain.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDetailDTO;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Product;
import ar.com.plug.examen.entities.Transaction;
import ar.com.plug.examen.entities.TransactionDetail;

@Component
public class TransactionMapper {
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private TransactionDetailMapper transactionDetailMapper;
	
	public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setStatus(transaction.getStatus());
		transactionDTO.setClient(this.clientMapper.clientEntityToClientDTO(transaction.getClient()));
		transactionDTO.setTransactionDetails(this.transactionDetailMapper.transactionDetailToListTransactionDetailsDTO(transaction.getTransactionDetails()));
		transactionDTO.setTransactionDate(transaction.getDate());
	    return transactionDTO;
	  }

	  public Transaction transactionDTOtoTransaction(TransactionDTO transactionDTO) {
	    Transaction transaction = new Transaction();
	    transaction.setClient(this.clientMapper.clientDTOToClientEntity(transactionDTO.getClient()));
	    transaction.setStatus(transactionDTO.getStatus());
	    transaction.setDate(transactionDTO.getTransactionDate());
	    transaction.setTransactionDetails(this.transactionDetailMapper.transactionDetailsDTOToListTransactionDetails(transactionDTO.getTransactionDetails()));
	    transaction.setStatus(transactionDTO.getStatus());
	    return transaction;
	  }

	  public List<TransactionDTO> transactionsToListTransactionDTO(List<Transaction> transaction) {
	    return transaction.stream().map(this::transactionToTransactionDTO).collect(Collectors.toList());
	  }

}
