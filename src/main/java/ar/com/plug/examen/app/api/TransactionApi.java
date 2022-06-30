package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonRootName(value = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TransactionApi {

    private Long id;
    private Long productId;
    private Long clientId;
    private Long sellerId;
    private Double price;
    private String status;
    private LocalDateTime date;

}
