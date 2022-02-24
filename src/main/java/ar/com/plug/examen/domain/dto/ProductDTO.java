package ar.com.plug.examen.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class ProductDTO {
    private long idProduct;
    private String descriptionProduct;
    private String category;
    private BigDecimal price;
    private Integer stock;
}
