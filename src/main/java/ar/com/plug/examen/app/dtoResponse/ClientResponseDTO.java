package ar.com.plug.examen.app.dtoResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponseDTO {

    @JsonProperty("http_status")
    private String status;

    @JsonProperty("status_code")
    private String code;

    @JsonProperty("client_id")
    private Long clientId;

}
