package ar.com.plug.examen.app.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;


@JsonRootName(value = "purchaseTransaction")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PurchaseTransactionApi {


    @JsonProperty
    private Long id;
    @JsonProperty
    private ProductApi idProduct;
    @JsonProperty
    private CustomerApi idCustomer;
    @JsonProperty
    private Long idWorker;


}
