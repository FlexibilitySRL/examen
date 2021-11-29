package ar.com.plug.examen.app.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long stock;

}
