package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a purchase entity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Purchase {

    @Id
    private UUID id;
    @OneToOne
    private Customer customer;
    @Setter
    @OneToOne
    private Vendor vendor;
    @Setter
    private PurchaseStatus status;
    private BigDecimal price;

    public Purchase(Customer customer, Vendor vendor, PurchaseStatus status, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.vendor = vendor;
        this.status = status;
        this.price = price;
    }

    /**
     * Represents the status of a purchase.
     */
    public enum PurchaseStatus {
        DRAFT, COMPLETED, APPROVED, CLOSED, SOLD, CANCELED
    }
}
