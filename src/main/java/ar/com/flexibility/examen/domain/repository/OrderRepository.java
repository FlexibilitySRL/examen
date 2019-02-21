/**
 * 
 */
package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Order;

/**
 * @author ro
 *
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

}
