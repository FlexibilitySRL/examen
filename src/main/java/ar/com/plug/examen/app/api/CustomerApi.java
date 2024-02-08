package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * API class representing a customer.
 */
@Value
@NoArgsConstructor(force = true)
@JsonRootName(value = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerApi {

    /**
     * Name of the customer.
     */
    @JsonProperty
    String name;

}
