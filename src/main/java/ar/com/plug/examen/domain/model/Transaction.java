package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Transaction extends PersistableEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seller_id", referencedColumnName = "id")
  private Seller seller;

  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
  private List<TransactionItems> transactionItems;

  @NotNull
  private Date creationDate;

  private TransactionStatusEnum status;

}
