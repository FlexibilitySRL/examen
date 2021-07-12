package ar.com.plug.examen.app.api;

import java.util.List;
import lombok.Data;

@Data
public class TransactionApiRquest {

  private Long clientId;
  private Long sellerId;
  private List<ProductStockApi> lsProducts;

}
