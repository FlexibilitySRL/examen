package ar.com.plug.examen.objects;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.model.SaleModel;
import ar.com.plug.examen.domain.model.SellerModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
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
    private SaleModel saleModel;
    private List<SaleModel> saleModels;
    private boolean confirmSale;

    private String jsonMessage;

    public JsonResponseTransaction(@NonNull JsonResponseTransaction jsonResponseTransaction){
        this.productModel=jsonResponseTransaction.getProductModel();
        this.statusTransaction=jsonResponseTransaction.getStatusTransaction();
        this.responseMessage=jsonResponseTransaction.getResponseMessage();
        this.customerModel=jsonResponseTransaction.getCustomerModel();
        this.sellerModel = jsonResponseTransaction.getSellerModel();
        this.saleModel = jsonResponseTransaction.getSaleModel();
        this.saleModels = jsonResponseTransaction.getSaleModels();
        this.confirmSale = jsonResponseTransaction.isConfirmSale();
        this.jsonMessage = jsonResponseTransaction.getJsonMessage();
    }

}
