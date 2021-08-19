package ar.com.plug.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="items_lines")
public class ItemByLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
    @SequenceGenerator(name="item_generator", sequenceName = "item_seq", allocationSize=50)
    @Column(name="id_item")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "quantity"})
    private Product product;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="total_amount_product")
    private Double totalAmountProduct;

    @Column(name="id_status")
    private Integer idStatus;

    @ManyToOne
    @JoinColumn(name = "FK_SALE", nullable = false, updatable = false)
    @JsonIgnoreProperties({"customer", "seller", "dateTrx","subTotal","total","tax","idStatus", "items","paymentType"})
    private Sale sale;

    @Override
    public String toString() {
        return "ItemByLine{" +
                "id=" + id +
                ", product ='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", totalAmountProduct='" + totalAmountProduct + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }

}
