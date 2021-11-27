package ar.com.plug.examen.app.api;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductStockApi {
    
  private Long idProduct;
  private Long quantity;
    
}
