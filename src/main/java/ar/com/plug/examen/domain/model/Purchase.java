package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.app.api.PurchaseProductDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase {

    @JsonProperty(value = "purchase_id")
    private Long purchaseId;

    @JsonProperty(value = "receipt_id")
    private Long receiptId;

    @JsonProperty(value = "client_document")
    private Integer clientDocument;

    @JsonProperty(value = "products_list")
    private List<PurchaseProductDTO> purchaseProductsList;

    @NotNull(message = "idseller required")
    @JsonProperty(value = "seller_id")
    private Long sellerId;

    @JsonProperty(value = "purchase_date")
    private Date purchaseDate;

    @JsonProperty(value = "status")
    private String status;
}
