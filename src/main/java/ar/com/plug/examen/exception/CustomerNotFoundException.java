package ar.com.plug.examen.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Exception thrown when a customer with a specified ID is not found.
 */
@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
    /**
     * Constructs a new  CustomerNotFoundException with the specified cart ID.
     *
     * @param customerId
     */
    public CustomerNotFoundException(UUID customerId) {
        super("Customer not found with ID: " + customerId);
    }
}
