package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

/**
 * Represents a vendor entity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private VendorStatus status;

    /**
     * Constructs a new vendor with the given name.
     */
    public Vendor(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = VendorStatus.ACTIVE;
    }

    public enum VendorStatus {
        ACTIVE, INACTIVE, SUSPENDED, UNDER_REVIEW, RETIRED
    }
}
