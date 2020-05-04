package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Shopping Cart repository to manage CRUD operations for the ShoppingCart POJO.
 *  Additionally, it exposes query methods to get completed carts and carts by
 *  client.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart getShoppingCartById(Long id);

    List<ShoppingCart> getShoppingCartsByCompleted(Boolean completed);

    @Query("SELECT s from carts s WHERE s.client = :client and s.completed = false")
    ShoppingCart getOpenShoppingCartByClient(@Param("client")Client client);
}
