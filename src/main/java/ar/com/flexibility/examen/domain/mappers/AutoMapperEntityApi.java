package ar.com.flexibility.examen.domain.mappers;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses=AutoMapperEntityApi.class)
public interface AutoMapperEntityApi {

    ClientApi sourceToDestinationClient(Client client);

    ProductApi sourceToDestinationProduct(Product product);

    TransactionApi sourceToDestinationTransaction(Transaction transaction);

    List<ClientApi> sourceToDestinationClients(List<Client> clients);

    List<ProductApi> sourceToDestinationProducts(List<Product> products);

    List<TransactionApi> sourceToDestinationTransactions(List<Transaction> transactions);
}