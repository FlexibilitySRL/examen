package ar.com.plug.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_generator")
    @SequenceGenerator(name="sale_generator", sequenceName = "sale_seq", allocationSize=50)
    @Column(name="id_sale")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Seller seller;

    @Column(name="date_trx")
    private String dateTrx;

    @Column(name="sub_total")
    private Double subTotal;

    @Column(name="tax")
    private Double tax;

    @Column(name="total")
    private Double total;

    @Column(name="id_status")
    private Integer idStatus;

    @ManyToOne
    @JoinColumn(name = "payment_type")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "availableBalance","idStatus"})
    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "sale")
    private List<ItemByLine> items;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", Customer='" + customer + '\'' +
                ", Seller='" + seller + '\'' +
                ", dateTrx='" + dateTrx + '\'' +
                ", sub_total='" + subTotal + '\'' +
                ", total='" + total + '\'' +
                ", tax='" + tax + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }
}
