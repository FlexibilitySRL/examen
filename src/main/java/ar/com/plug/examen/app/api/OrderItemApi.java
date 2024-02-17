package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemApi {
    private String sku;
    private Double price;
    private Long quantity;
}
