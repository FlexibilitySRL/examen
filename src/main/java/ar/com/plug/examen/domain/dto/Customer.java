package ar.com.plug.examen.domain.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_customer")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="id_status")
    private Integer id_status;

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + id +
                ", name='" + name + '\'' +
                ", status='" + id_status + '\'' +
                '}';
    }
}
