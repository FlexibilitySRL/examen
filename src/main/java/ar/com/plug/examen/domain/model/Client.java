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
public class Client extends PersistableEntity{

  @NotNull
  private String userName;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private Long age;

  @NotNull
  private String email;

  @OneToMany(mappedBy="client", cascade= CascadeType.ALL)
  private List<Transaction> transaction;

}
