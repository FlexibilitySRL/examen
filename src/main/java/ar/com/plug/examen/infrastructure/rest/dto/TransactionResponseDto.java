package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.plug.examen.domain.Transaction;
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
public class TransactionResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Boolean approved;
    private String clientId;
    private Double total;
    private LocalDateTime date;
    private List<ItemProductResponseDto> items;

    public TransactionResponseDto(Transaction transacction) {
        this.id = transacction.getId();
        this.approved = transacction.getApproved();
        this.clientId = transacction.getClientId();
        this.total = transacction.getTotal();
        this.date = transacction.getDate();
        this.items = transacction.getItems().stream().map(ItemProductResponseDto::new).collect(Collectors.toList());
    }
}
