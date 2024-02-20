package ar.com.plug.examen.domain;

import java.time.LocalDateTime;
import java.util.List;

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
public class Transaction {

    private String id;
    private Boolean approved;
    private String clientId;
    private Double total;
    private LocalDateTime date;
    private List<ItemProduct> items;
}
