package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.Transaction;
import ar.com.plug.examen.shared.config.MenssageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Boolean approved;

    @NotEmpty(message = MenssageResponse.T402)
    private String clientId;

    private Double total;
    private LocalDateTime date;

    @NotNull(message = MenssageResponse.T403)
    private List<ItemProductRequestDto> items;

    public Transaction toTransaction() {
        return Transaction
                .builder().clientId(clientId)
                .items(items.stream().map(ItemProductRequestDto::toItemProduct).collect(Collectors.toList()))
                .build();
    }
}
