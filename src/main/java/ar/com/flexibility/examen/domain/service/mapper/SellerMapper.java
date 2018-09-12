package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Seller and its Api SellerApi.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface SellerMapper extends EntityMapper<SellerApi, Seller> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    SellerApi toApi(Seller seller);

    @Mapping(source = "userId", target = "user")
    Seller toEntity(SellerApi sellerApi);

    default Seller fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seller seller = new Seller();
        seller.setId(id);
        return seller;
    }
}
