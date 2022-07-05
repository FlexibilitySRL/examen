package ar.com.plug.examen.app.dtoResponse;

import ar.com.plug.examen.domain.model.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListSellerResponseDTO {

    @JsonProperty("sellers")
    private List<Seller> sellers;
}
