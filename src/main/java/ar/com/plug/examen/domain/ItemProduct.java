package ar.com.plug.examen.domain;

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
public class ItemProduct {

    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Double price;
}
