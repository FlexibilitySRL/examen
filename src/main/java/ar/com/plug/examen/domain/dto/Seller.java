package ar.com.plug.examen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seller")
public class Seller {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_generator")
   @SequenceGenerator(name="seller_generator", sequenceName = "seller_seq", allocationSize=50)
   @Column(name="id_seller")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="id_status")
    private Integer idStatus;

    @Override
    public String toString() {
        return "Seller{" +
                "idSeller=" + id +
                ", name='" + name + '\'' +
                ", status='" + idStatus + '\'' +
                '}';
    }
}
