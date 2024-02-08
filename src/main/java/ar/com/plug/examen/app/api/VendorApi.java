package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Value;

import java.util.UUID;

/**
 * API class representing a vendor.
 */
@Value
@JsonRootName(value = "vendor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorApi {

    /**
     * Unique identifier for the vendor.
     */
    @JsonProperty
    UUID id;

    /**
     * Name of the vendor.
     */
    @JsonProperty
    String name;
}
