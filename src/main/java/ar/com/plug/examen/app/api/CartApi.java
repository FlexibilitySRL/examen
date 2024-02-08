package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 * API class representing a shopping cart.
 */
@Value
@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartApi {

    /**
     * List of items in the cart.
     */
    @JsonProperty
    List<CartItemApi> items;

    /**
     * Customer associated with the cart.
     */
    @JsonProperty
    CustomerApi customer;

    /**
     * Purchase details associated with the cart.
     */
    @JsonProperty
    PurchaseApi purchase;

    @Value
    public static class CartItemApi {

        /**
         * Product details for an item in the cart.
         */
        @JsonProperty
        UUID productId;

        /**
         * Quantity of the product in the cart.
         */
        @JsonProperty
        Integer quantityApi;
    }
}
