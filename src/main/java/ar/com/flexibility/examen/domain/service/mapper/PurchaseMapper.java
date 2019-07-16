package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.dto.PurchaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface PurchaseMapper {
    PurchaseDTO toDTO(Purchase purchase);

    Purchase toEntity(PurchaseDTO purchaseDTO);
}
