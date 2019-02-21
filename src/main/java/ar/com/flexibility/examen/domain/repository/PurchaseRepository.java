/**
 * 
 */
package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purchase;


/**
 * @author rosalizaracho
 *
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	List<Purchase> findByClient(Client client);

}
