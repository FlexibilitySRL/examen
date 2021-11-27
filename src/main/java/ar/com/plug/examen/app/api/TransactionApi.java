package ar.com.plug.examen.app.api;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import java.util.Date;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionApi {
    
  private Long id;
  private ClientApi client;
  private SellerApi seller;
  private List<TransactionItemsApi> transactionItems;
  private Date creationDate;
  private TransactionStatusEnum status;
  
  public TransactionApi(ClientApi client, SellerApi seller,
      List<TransactionItemsApi> transactionItems, Date creationDate,
      TransactionStatusEnum status) {
    this.client = client;
    this.seller = seller;
    this.transactionItems = transactionItems;
    this.creationDate = creationDate;
    this.status = status;
  }
    
}
