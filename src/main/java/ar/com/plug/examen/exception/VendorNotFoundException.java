package ar.com.plug.examen.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Exception thrown when a vendor with a specified ID is not found.
 */
@NoArgsConstructor
public class VendorNotFoundException extends RuntimeException {
    /**
     * Constructs a new  VendorNotFoundException with the specified cart ID.
     *
     * @param vendorId
     */
    public VendorNotFoundException(UUID vendorId) {
        super("Vendor not found with ID: " + vendorId);
    }
}
