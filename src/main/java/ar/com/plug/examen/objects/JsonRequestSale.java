package ar.com.plug.examen.objects;

import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.model.PaymentTypeModel;
import ar.com.plug.examen.domain.model.SellerModel;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonRequestSale {
    private CustomerModel customerModel;
    private SellerModel sellerModel;
    private Set<RequestCustomer> productMapRequest;
    private PaymentTypeModel paymentType;
    private Double totalRequiredByPayment;
}
