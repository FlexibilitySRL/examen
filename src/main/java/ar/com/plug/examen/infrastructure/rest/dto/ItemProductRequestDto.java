package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.ItemProduct;
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
public class ItemProductRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    @NotEmpty(message = MenssageResponse.T402)
    private Integer productId;

    @Min(value = 0, message = MenssageResponse.T404)
    @NotNull(message = MenssageResponse.T404)
    private Integer quantity;

    @Min(value = 0, message = MenssageResponse.P403)
    @NotNull(message = MenssageResponse.P403)
    private Double price;

    public ItemProduct toItemProduct() {
        return ItemProduct.builder().productId(productId)
                .quantity(quantity).price(price)
                .build();
    }
}
