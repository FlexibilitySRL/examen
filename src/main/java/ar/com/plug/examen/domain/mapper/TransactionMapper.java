package ar.com.plug.examen.domain.mapper;


import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemsTrasactionMapper.class})
public interface TransactionMapper {

   @Mappings({
           @Mapping(source = "id", target = "transantionId"),
           @Mapping(source = "customerId", target = "customerId"),
           @Mapping(source = "customer.name", target = "customerName"),
           @Mapping(source = "customer.lastName", target = "customerLastName"),
           @Mapping(source = "sellerId", target = "sellerId"),
           @Mapping(source = "seller.name", target = "sellerName"),
           @Mapping(source = "seller.lastName", target = "sellerLastName"),
           @Mapping(source = "date", target = "date"),
           @Mapping(source = "state", target = "state"),
           @Mapping(source = "itemsTransaction", target = "itemsTransaction")

   })
   TransactionDTO toTransactionDto(Transaction transaction);
   List<TransactionDTO> toListTransactionsDto(List<Transaction> transactionsList);

   @InheritInverseConfiguration
   @Mappings({
           @Mapping(target = "customer", ignore = true),
           @Mapping(target = "seller", ignore = true)
   })
   Transaction toTransaction (TransactionDTO transactionDTO);
}
