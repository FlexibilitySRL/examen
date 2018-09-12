package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.app.api.SellApi;
import ar.com.flexibility.examen.domain.model.Sell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Sell and its Api SellApi.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, SellerMapper.class, ClientMapper.class})
public interface SellMapper extends EntityMapper<SellApi, Sell> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "client.id", target = "clientId")
    SellApi toApi(Sell sell);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "sellerId", target = "seller")
    @Mapping(source = "clientId", target = "client")
    Sell toEntity(SellApi sellApi);

    default Sell fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sell sell = new Sell();
        sell.setId(id);
        return sell;
    }
}
