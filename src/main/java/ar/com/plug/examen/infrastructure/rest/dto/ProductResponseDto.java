package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.Product;
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
public class ProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Integer inventory;
    private Double price;
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
