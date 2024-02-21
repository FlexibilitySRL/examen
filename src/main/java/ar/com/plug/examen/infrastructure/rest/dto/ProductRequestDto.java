package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.shared.config.MenssageResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotBlank(message = MenssageResponse.P401)
    private String name;
    private String description;

    @NotNull(message = MenssageResponse.P402)
    @Min(value = 0, message = MenssageResponse.P402)
    private Integer inventory;

    @NotNull(message = MenssageResponse.P403)
    @Min(value = 0, message = MenssageResponse.P403)
    private Double price;

    @NotBlank(message = MenssageResponse.P406)
    private String barCode;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .inventory(inventory)
                .price(price)
                .barCode(barCode)
                .build();
    }
}
