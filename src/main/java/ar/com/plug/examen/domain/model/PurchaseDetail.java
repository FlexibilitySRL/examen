package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.enums.PurchaseState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchaseDetail;

    @Enumerated(EnumType.STRING)
    private PurchaseState purchaseState;

    @CreationTimestamp
    private LocalDateTime createDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    @Exclude
    private Purchase purchase;
}
