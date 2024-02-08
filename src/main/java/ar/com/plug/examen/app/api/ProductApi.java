package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

/**
 * API class representing a product.
 */
@Value
@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(force = true)
public class ProductApi {

    /**
     * Name of the product.
     */
    @JsonProperty
    String name;

    /**
     * Description of the product.
     */
    @JsonProperty
    String description;

    /**
     * Price of the product.
     */
    @JsonProperty
    BigDecimal price;
}
