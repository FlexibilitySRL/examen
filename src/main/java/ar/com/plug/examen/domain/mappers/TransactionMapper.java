package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Transaction;

@Component
public class TransactionMapper {
	
	@Autowired
	private ClientMapper clientMapper;
	
	public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setStatus(transaction.getStatus());
		transactionDTO.setClient(this.clientMapper.clientEntityToClientDTO(transaction.getClient()));
	    return transactionDTO;
	  }

	  public Transaction transactionDTOtoTransaction(TransactionDTO transactionDTO) {
	    Transaction transaction = new Transaction();
	    transaction.setClient(this.clientMapper.clientDTOToClientEntity(transactionDTO.getClient()));
	    transaction.setStatus(transactionDTO.getStatus());
	    return transaction;
	  }

	  public List<TransactionDTO> transactionsToListTransactionDTO(List<Transaction> transaction) {
	    return transaction.stream().map(this::transactionToTransactionDTO).collect(Collectors.toList());
	  }

}
