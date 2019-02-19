/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import java.util.List;

import javax.persistence.Entity;

/**
 * @author rosalizaracho
 *
 */
@Entity
public class Purchase {
	List<Product> productList;
}
