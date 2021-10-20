package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.domain.dto.ItemTransactionDTO;
import ar.com.plug.examen.domain.model.ItemTransaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemsTrasactionMapper {

    @Mappings({
            @Mapping(source = "id.transactionId", target = "transactionId"),
            @Mapping(source = "id.productId", target = "productId"),
            @Mapping(source = "product.name", target = "productName"),
            @Mapping(source = "product.description", target = "productDescription"),
            @Mapping(source = "product.price", target = "price"),
            @Mapping(source = "amount", target = "amount"),
            @Mapping(source = "total", target = "total")
    })
    ItemTransactionDTO toItemsTransactionsDto(ItemTransaction itemTransaction);
    List<ItemTransactionDTO> toListItemsTransactionDto(List<ItemTransaction> itemTransactionList);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "product", ignore = true)
    })
    ItemTransaction toItemTransaction(ItemTransactionDTO itemTransactionDTO);
}
