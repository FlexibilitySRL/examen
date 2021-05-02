package ar.com.plug.examen.datasource.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Purchase {

    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false)
    boolean approved;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date approvalDateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @OneToMany(mappedBy = "purchase")
    List<ProductPurchase> productPurchases;

}
