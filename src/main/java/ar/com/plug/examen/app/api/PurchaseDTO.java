package ar.com.plug.examen.app.api;

import ar.com.plug.examen.app.common.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Range;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDTO {

    @JsonProperty(value = "purchase_id")
    private Long purchaseId;

    @JsonProperty(value = "receipt_id")
    private Long receiptId;

    @JsonProperty(value = "client_document")
    @NotNull(message = "client_document" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Range(min = 1, max = 99999999, message = "client_document" + ValidationMessages.DOCUMENT_NUMBER_VALID_VALUES)
    private Integer clientDocument;

    @Valid
    @JsonProperty(value = "purchase_products")
    @NotNull(message = "purchaseProductDTOList" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    private List<PurchaseProductDTO> purchaseProductsList;

    @NotNull(message = "seller_id" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @JsonProperty(value = "seller_id")
    private Long sellerId;

    @JsonProperty(value = "purchase_date")
    private Date purchaseDate;

    @JsonProperty(value = "status")
    private String status;
}
