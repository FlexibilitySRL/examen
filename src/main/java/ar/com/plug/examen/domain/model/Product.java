package ar.com.plug.examen.domain.model;

import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Product extends PersistableEntity {

  @NotNull
  private String name;

  private String description;

  @NotNull
  private Double price;

  @NotNull
  private Long stock;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<TransactionItems> transactionItems;

}
