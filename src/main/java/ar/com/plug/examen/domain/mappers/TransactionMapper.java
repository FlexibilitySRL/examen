package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.model.Transaction;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionMapper {
    
    @Autowired
    ClientMapper clientMapper;
    
    @Autowired
    SellerMapper sellerMapper;
    
    @Autowired
    TransactionItemsMapper transactionItemsMapper;
    
    public TransactionApi transactionToTransactionApi (Transaction transaction) {
        
        TransactionApi transactionApi = TransactionApi
                .builder()
                .client(clientMapper.clientToClientApi(transaction.getClient()))
                .creationDate(transaction.getCreationDate())
                .id(transaction.getId())
                .seller(sellerMapper.sellerToSellerApi(transaction.getSeller()))
                .status(transaction.getStatus())
                .transactionItems(transactionItemsMapper.transactionItemsToListTransactionitemsApi(transaction.getTransactionItems()))
                .build();
        
        return transactionApi;
    }
    
    public Transaction transactionApiToTransaction (TransactionApi transactionApi) {
        
        Transaction transaction = Transaction
                .builder()
                .client(clientMapper.clientApiToClient(transactionApi.getClient()))
                .creationDate(transactionApi.getCreationDate())
                .id(transactionApi.getId())
                .seller(sellerMapper.sellerApiToSeller(transactionApi.getSeller()))
                .status(transactionApi.getStatus())
                .transactionItems(transactionItemsMapper.transactionItemsApiToListTransactionitems(transactionApi.getTransactionItems()))
                .build();
        
        return transaction;
    }
    
    public List<TransactionApi> transactionsToListTransactionApi(List<Transaction> transactions) {
    return transactions.stream().map(this::transactionToTransactionApi).collect(Collectors.toList());
  }
    
}