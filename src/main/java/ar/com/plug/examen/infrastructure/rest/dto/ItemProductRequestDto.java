package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.ItemProduct;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemProductRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico del item de producto", example = "1", required = false)
    private String id;

    @Schema(description = "Identificador unico del producto del item de producto", example = "1", required = true)
    @NotEmpty(message = MenssageResponse.T402)
    private Integer productId;

    @Schema(description = "Cantidad del producto ", example = "1", required = true)
    @Min(value = 0, message = MenssageResponse.T404)
    @NotNull(message = MenssageResponse.T404)
    private Integer quantity;

    
    @Schema(description = "Precio del producto ", example = "1", required = true)
    @Min(value = 0, message = MenssageResponse.P403)
    @NotNull(message = MenssageResponse.P403)
    private Double price;

    public ItemProduct toItemProduct() {
        return ItemProduct.builder().productId(productId)
                .quantity(quantity).price(price)
                .build();
    }
}
