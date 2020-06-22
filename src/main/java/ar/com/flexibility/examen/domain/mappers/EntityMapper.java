package ar.com.flexibility.examen.domain.mappers;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.app.api.VendorApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    ProductApi toProductApi(Product product);

    ClientApi toClientApi(Client client);

    VendorApi toVendorApi(Vendor vendor);

    TransactionApi toTransactionApi(Transaction transaction);

    List<ProductApi> toProductsApi(List<Product> products);

    List<ClientApi> toClientsApi(List<Client> clients);

    List<VendorApi> toVendorsApi(List<Vendor> vendor);

    List<TransactionApi> toTransactionsApi(List<Transaction> transactions);

}
