package ar.com.plug.examen.app.api;

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

import ar.com.plug.examen.app.common.ValidationMessages;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @JsonProperty(value = "product_id")
    private Long productId;

    @NotEmpty(message = "product_code" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 8, message = "product_code" + ValidationMessages.SIZE_MIN_AND_MAX)
    @JsonProperty(value = "product_code")
    private String productCode;

    @NotEmpty(message = "product_name" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 30, message = "product_name" + ValidationMessages.SIZE_MIN_AND_MAX)
    @JsonProperty(value = "product_name")
    private String productName;

    @NotEmpty(message = "product_description" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 50, message = "product_name" + ValidationMessages.SIZE_MIN_AND_MAX)
    @JsonProperty(value = "product_description")
    private String productDescription;

    @NotNull(message = "product_price" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Range(min = 0, max = 99999999, message = "product_price" + ValidationMessages.RANGE_MIN_AND_MAX)
    @JsonProperty(value = "product_price")
    private Double productPrice;

}