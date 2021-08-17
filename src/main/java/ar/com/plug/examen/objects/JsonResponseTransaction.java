package ar.com.plug.examen.objects;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.model.SellerModel;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonResponseTransaction implements Serializable {
    private static final long serialVersionUID = 5733456939053366488L;

    private ProductModel productModel;
    private StatusTransaction statusTransaction;
    private String responseMessage;
    private CustomerModel customerModel;
    private SellerModel sellerModel;

    public JsonResponseTransaction(@NonNull JsonResponseTransaction jsonResponseTransaction){
        this.productModel=jsonResponseTransaction.getProductModel();
        this.statusTransaction=jsonResponseTransaction.getStatusTransaction();
        this.responseMessage=jsonResponseTransaction.getResponseMessage();
        this.customerModel=jsonResponseTransaction.getCustomerModel();
        this.sellerModel = jsonResponseTransaction.getSellerModel();
    }

}
