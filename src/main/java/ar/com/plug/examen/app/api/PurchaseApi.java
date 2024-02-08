package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * API class representing a purchase.
 */
@Value
@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseApi {

    /**
     * Customer associated with the purchase.
     */
    @JsonProperty
    UUID customerId;

    /**
     * Status of the purchase.
     */
    @JsonProperty
    UUID vendorId;

    /**
     * Status of the purchase.
     */
    @JsonProperty
    String statusApi;

    /**
     * Status of the purchase.
     */
    @JsonProperty
    BigDecimal priceApi;

}
