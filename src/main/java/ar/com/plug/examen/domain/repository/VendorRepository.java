package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Vendor entities.
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    /**
     * Retrieves a first of vendors by a status.
     */
    Optional<Vendor> findFirstByStatus(Vendor.VendorStatus status);

}
