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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_seq", allocationSize=50)
    @Column(name="id_customer")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="id_status")
    private Integer idStatus;

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + id +
                ", name='" + name + '\'' +
                ", status='" + idStatus + '\'' +
                '}';
    }
}
