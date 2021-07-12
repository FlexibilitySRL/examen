package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TransactionItemsApi {

  private ProductApi product;
  private Long quantity;
  @JsonIgnore
  private Transaction transaction;

}
