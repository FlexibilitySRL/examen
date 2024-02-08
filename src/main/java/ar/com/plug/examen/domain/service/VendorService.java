package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.VendorDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Vendor operations.
 */
public interface VendorService {

    /**
     * Adds a new vendor.
     *
     * @param vendorDTO The vendor details.
     */
    VendorDTO addVendor(VendorDTO vendorDTO);

    /**
     * Updates an existing vendor.
     *
     * @param vendorId  The unique identifier of the vendor.
     * @param vendorDTO The updated vendor details.
     */
    VendorDTO updateVendor(UUID vendorId, VendorDTO vendorDTO);

    /**
     * Deletes a vendor.
     *
     * @param vendorId The unique identifier of the vendor.
     */
    void deleteVendor(UUID vendorId);

    /**
     * Retrieves a list of all vendors.
     *
     * @return List of all vendors.
     */
    List<VendorDTO> getAllVendors();

    /**
     * Retrieves a vendor by its unique identifier.
     *
     * @param vendorId The unique identifier of the vendor.
     * @return Optional containing the vendor details, or empty if not found.
     */
    Optional<VendorDTO> getVendorById(UUID vendorId);
}
