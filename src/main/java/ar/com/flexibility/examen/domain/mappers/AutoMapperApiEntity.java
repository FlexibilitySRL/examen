package ar.com.flexibility.examen.domain.mappers;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses=AutoMapperEntityApi.class)
public interface AutoMapperApiEntity {

    Client sourceToDestinationClientApi(ClientApi clientApi);

    Product sourceToDestinationProductApi(ProductApi productApi);

    Transaction sourceToDestinationTransactionApi(TransactionApi transactionApi);

}