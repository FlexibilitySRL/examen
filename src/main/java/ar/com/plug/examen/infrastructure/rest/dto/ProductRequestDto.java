package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico del producto", example = "1", required = false)
    private Integer id;

    @Schema(description = "Nombre del producto", example = "notebook hp-1500", required = true)
    @NotBlank(message = MenssageResponse.P401)
    private String name;

    @Schema(description = "Descripcion del producto", example = "computadore personal", required = true)
    private String description;

    @Schema(description = "Cantidad de producto a ingresar", example = "10", required = true)
    @NotNull(message = MenssageResponse.P402)
    @Min(value = 0, message = MenssageResponse.P402)
    private Integer inventory;

    @Schema(description = "Precio de producto a ingresar", example = "10.0", required = true)
    @NotNull(message = MenssageResponse.P403)
    @Min(value = 0, message = MenssageResponse.P403)
    private Double price;

    @Schema(description = "Codigo de barra unico del producto a ingresar", example = "AF001A", required = true)
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
