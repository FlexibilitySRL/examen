package ar.com.plug.examen.app.api;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductApi {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long stock;

}
