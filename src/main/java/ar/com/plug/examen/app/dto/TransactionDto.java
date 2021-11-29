package ar.com.plug.examen.app.dto;

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
public class TransactionDto {
    
  private Long id;
  private ClientDto client;
  private SellerDto seller;
  private List<TransactionItemsDto> transactionItems;
  private Date creationDate;
  private TransactionStatusEnum status;
  
  public TransactionDto(ClientDto client, SellerDto seller,
      List<TransactionItemsDto> transactionItems, Date creationDate,
      TransactionStatusEnum status) {
    this.client = client;
    this.seller = seller;
    this.transactionItems = transactionItems;
    this.creationDate = creationDate;
    this.status = status;
  }
    
}
