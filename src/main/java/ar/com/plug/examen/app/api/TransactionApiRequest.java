package ar.com.plug.examen.app.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionApiRequest {
    
  private Long clientId;
  private Long sellerId;
  private List<ProductStockApi> listProducts;
    
}
