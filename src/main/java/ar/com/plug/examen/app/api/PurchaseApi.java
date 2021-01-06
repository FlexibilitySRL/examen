package ar.com.plug.examen.app.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@JsonRootName(value = "purchase")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PurchaseApi {


    @JsonProperty
    private Long id;
    @JsonProperty
    @NotNull
    private Long idProduct;
    @JsonProperty
    @NotNull
    private Long idCustomer;
    @JsonProperty
    @NotNull
    private Long idWorker;

}
