package ar.com.flexibility.examen.domain.mappers;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityApiMapper {

    ProductApi sourceToDestinationProduct(Product product);

    ClientApi sourceToDestinationClient(Client client);

    SellerApi sourceToDestinationSeller(Seller seller);

    TransactionApi sourceToDestinationTransaction(Transaction transaction);

    List<ProductApi> sourceToDestinationProducts(List<Product> products);

    List<ClientApi> sourceToDestinationClients(List<Client> clients);

    List<SellerApi> sourceToDestinationSellers(List<Seller> sellers);

    List<TransactionApi> sourceToDestinationTransactions(List<Transaction> transactions);

    Product destinationToSourceProductApi(ProductApi productApi);

    Client destinationToSourceClientApi(ClientApi clientApi);

    Seller destinationToSourceSellerApi(SellerApi sellerApi);

    Transaction destinationToSourceTransactionApi(TransactionApi transactionApi);

}
