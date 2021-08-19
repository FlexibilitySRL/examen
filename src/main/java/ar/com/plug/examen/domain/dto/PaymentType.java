package ar.com.plug.examen.domain.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize=50)
    @Column(name="id")
    private Long id;

    @Column(name="currency")
    private String currency;

    @Column(name="type")
    private String type;

    @Column(name="availableBalance")
    private Double availableBalance;

    @Column(name="idStatus")
    private Integer idStatus;

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", currency ='" + currency + '\'' +
                ", type='" + type + '\'' +
                ", availableBalance='" + availableBalance + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }
}
