package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart getShoppingCartById(Long id);

    List<ShoppingCart> getShoppingCartsByCompleted(Boolean completed);

    @Query("SELECT s from carts s WHERE s.client = :client and s.completed = false")
    ShoppingCart getOpenShoppingCartByClient(@Param("client")Client client);
}
