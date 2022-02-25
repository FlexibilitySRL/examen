package ar.com.plug.examen.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.ToString.Exclude;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchase;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    @Exclude
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller")
    @Exclude
    private Seller seller;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    @Exclude
    private Product product;

    @Column
    private String voucher;

    @CreationTimestamp
    private LocalDateTime datePurchase;

    @Column
    private BigDecimal taxes;

    @Column
    private BigDecimal amount;

    private LocalDateTime updateDate;
}
