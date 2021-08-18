package ar.com.plug.examen.domain.converter;


import ar.com.plug.examen.domain.dto.*;
import ar.com.plug.examen.domain.model.SaleModel;
import org.springframework.stereotype.Component;

@Component
public class SaleConverter extends Converter<Sale, SaleModel>{

    public SaleConverter() {
        super(SaleConverter::convertToEntity, SaleConverter::convertToModel);
    }

    private static Sale convertToModel(SaleModel saleModel) {
        return new Sale(saleModel.getId(), saleModel.getCustomer(), saleModel.getSeller(), saleModel.getDateTrx(), saleModel.getSubTotal(), saleModel.getTax(), saleModel.getTotal(), saleModel.getIdStatus(), saleModel.getPaymentType(), saleModel.getItems());
    }

    private static SaleModel convertToEntity(Sale dto) {
        return new SaleModel(dto.getId(), dto.getCustomer(), dto.getSeller(), dto.getDateTrx(), dto.getSubTotal(), dto.getTax(), dto.getTotal(), dto.getIdStatus(), dto.getPaymentType(), dto.getItems());
    }

}
