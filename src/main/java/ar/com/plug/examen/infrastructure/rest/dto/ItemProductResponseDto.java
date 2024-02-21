package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.ItemProduct;
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
public class ItemProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Double price;

    public ItemProductResponseDto(ItemProduct itemProduct) {
        this.id = itemProduct.getId();
        this.productId = itemProduct.getProductId();
        this.quantity = itemProduct.getQuantity();
        this.price = itemProduct.getPrice();
    }
}
