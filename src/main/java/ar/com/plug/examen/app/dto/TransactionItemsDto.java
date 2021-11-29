package ar.com.plug.examen.app.dto;

import ar.com.plug.examen.domain.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TransactionItemsDto {
    
  private ProductDto product;
  private Long quantity;
  @JsonIgnore
  private Transaction transaction;
    
}
