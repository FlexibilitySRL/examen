package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.ItemProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico del item de producto", example = "1")
    private Integer id;
    @Schema(description = "Identificador unico del producto del item de producto", example = "1")
    private Integer productId;
    @Schema(description = "Cantidad del producto ", example = "1")
    private Integer quantity;
    @Schema(description = "Precio del producto ", example = "1")
    private Double price;

    public ItemProductResponseDto(ItemProduct itemProduct) {
        this.id = itemProduct.getId();
        this.productId = itemProduct.getProductId();
        this.quantity = itemProduct.getQuantity();
        this.price = itemProduct.getPrice();
    }
}
