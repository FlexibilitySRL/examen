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

@Mapper(componentModel = "spring")
public interface ApiMapper {
    Product toProduct(ProductApi productApi);

    Client toClient(ClientApi clientApi);

    Vendor toVendor(VendorApi vendorApi);

    Transaction toTransaction(TransactionApi transactionApi);
}
