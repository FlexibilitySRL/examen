package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionApi {

    private TransactionType type;

    @NotNull(message = "clientId is required")
    private Long clientId;

    @NotNull(message = "vendorId is required")
    private Long vendorId;

    private List<Map<String, String>> products;
}
