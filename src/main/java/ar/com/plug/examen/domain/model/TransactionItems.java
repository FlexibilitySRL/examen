package ar.com.plug.examen.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class TransactionItems extends PersistableEntity {

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  private Long quantity;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "transaction_id", referencedColumnName = "id")
  private Transaction transaction;

}
