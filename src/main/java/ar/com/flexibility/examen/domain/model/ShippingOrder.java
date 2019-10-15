package ar.com.flexibility.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHIPPING_ORDER")
public class ShippingOrder {

    @Id
    @Column(name = "SHIPPING_ORDER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address shipTo;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @Column(name = "TOTAL")
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shippingOrder", cascade = ALL)
    private List<LineItem> lineItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    private Account account;

}
