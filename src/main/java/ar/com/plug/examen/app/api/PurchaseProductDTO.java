package ar.com.plug.examen.app.api;

import ar.com.plug.examen.app.common.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseProductDTO {

    @JsonProperty(value = "item")
    private Integer item;

    @JsonProperty(value = "product_code")
    @NotEmpty(message = "product_code " + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 8, message = "product_code " + ValidationMessages.SIZE_MIN_AND_MAX)
    private String productCode;

    @JsonProperty(value = "purchase_quantity")
    @NotNull(message = "purchase_quantity " + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Range(min = 1, max = 99999999, message = "purchase_quantity " + ValidationMessages.DOCUMENT_NUMBER_VALID_VALUES)
    private Integer purchaseQuantity;

    @JsonProperty(value = "product_price")
    @Range(min = 0, max = 99999999, message = "product_price " + ValidationMessages.RANGE_MIN_AND_MAX)
    private Double productPrice;
}
