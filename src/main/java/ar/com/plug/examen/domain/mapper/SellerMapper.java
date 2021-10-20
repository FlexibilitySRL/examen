package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    SellerDTO toSellerDto(Seller seller);
    List<SellerDTO> toListSellerDtos(List<Seller> sellerList);

    @InheritInverseConfiguration
    @Mapping(target = "transactions", ignore = true)
    Seller toSeller(SellerDTO sellerDTO);
}
