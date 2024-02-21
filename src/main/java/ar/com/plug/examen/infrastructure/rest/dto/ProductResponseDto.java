package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "Identificador unico del producto", example = "1", required = false)
    private Integer id;

    @Schema(description = "Nombre del producto", example = "notebook hp-1500", required = true)
    private String name;

    @Schema(description = "Descripcion del producto", example = "computadore personal", required = true)
    private String description;

    @Schema(description = "Cantidad de producto", example = "10", required = true)
    private Integer inventory;

    @Schema(description = "Precio de producto ", example = "10.0", required = true)
    private Double price;

    @Schema(description = "Codigo de barra unico del producto", example = "AF001A", required = true)
    private String barCode;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.inventory = product.getInventory();
        this.price = product.getPrice();
        this.barCode = product.getBarCode();
    }
}
