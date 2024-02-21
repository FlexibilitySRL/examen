package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.Transaction;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransactionRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico de la transaccion", example = "1", required = false)
    private String id;

    @Schema(description = "Campo de estado de la transaccion", example = "false", required = false)
    private Boolean approved;

    @Schema(description = "Id del cliente al cual pertenece la transaccion", example = "1", required = true)
    @NotEmpty(message = MenssageResponse.T402)
    private Integer clientId;

    @Schema(description = "Monto total de la transaccion", example = "10.1", required = false)
    private Double total;

    @Schema(description = "Fecha de la transaccion", example = "2024-02-20T21:59:53.876", required = false)
    private LocalDateTime date;

    @Schema(description = "Lista de items de los productos de la transaccion", required = true)
    @NotNull(message = MenssageResponse.T403)
    private List<ItemProductRequestDto> items;

    public Transaction toTransaction() {
        return Transaction
                .builder().clientId(clientId)
                .items(items.stream().map(ItemProductRequestDto::toItemProduct).collect(Collectors.toList()))
                .build();
    }
}
