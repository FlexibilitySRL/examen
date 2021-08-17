package ar.com.plug.examen.domain.converter;


import ar.com.plug.examen.domain.dto.Seller;
import ar.com.plug.examen.domain.model.SellerModel;
import org.springframework.stereotype.Component;


@Component
public class SellerConverter extends Converter<Seller, SellerModel> {

    public SellerConverter() {
        super(SellerConverter::convertToEntity, SellerConverter::convertToModel);
    }

    private static Seller convertToModel(SellerModel sellerModel) {
        return new Seller(sellerModel.getId(), sellerModel.getName(), sellerModel.getIdStatus());
    }

    private static SellerModel convertToEntity(Seller dto) {
        return new SellerModel(dto.getId(), dto.getName(), dto.getIdStatus());
    }

}