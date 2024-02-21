package ar.com.plug.examen.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Transaction {

    private Integer id;
    private Boolean approved;
    private Integer clientId;
    private Double total;
    private LocalDateTime date;
    private List<ItemProduct> items;
}
