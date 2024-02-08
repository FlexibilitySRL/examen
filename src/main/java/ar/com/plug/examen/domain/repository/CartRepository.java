package ar.com.plug.examen.domain.repository;


import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Cart entities.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    /**
     * Retrieves a list of carts associated with a specific customer.
     *
     * @param customer The customer entity.
     * @return List of carts associated with the specified customer.
     */
    List<Cart> findByCustomer(Customer customer);
}
