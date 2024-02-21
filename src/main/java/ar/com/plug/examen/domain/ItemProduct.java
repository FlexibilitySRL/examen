package ar.com.plug.examen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ItemProduct {

    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Double price;
}
