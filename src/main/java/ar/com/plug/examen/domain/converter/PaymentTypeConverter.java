package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.domain.dto.PaymentType;
import ar.com.plug.examen.domain.model.PaymentTypeModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeConverter extends Converter<PaymentType, PaymentTypeModel> {
    public PaymentTypeConverter() {
        super(PaymentTypeConverter::convertToEntity, PaymentTypeConverter::convertToModel);
    }

    private static PaymentType convertToModel(PaymentTypeModel model) {
        return new PaymentType(model.getId(), model.getCurrency(), model.getType(), model.getAvailableBalance(), model.getIdStatus());
    }

    private static PaymentTypeModel convertToEntity(PaymentType dto) {
        return new PaymentTypeModel(dto.getId(), dto.getCurrency(), dto.getType(), dto.getAvailableBalance(), dto.getIdStatus());
    }

}