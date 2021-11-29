package ar.com.plug.examen.app.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductStockDto {
    
  private Long idProduct;
  private Long quantity;
    
}
