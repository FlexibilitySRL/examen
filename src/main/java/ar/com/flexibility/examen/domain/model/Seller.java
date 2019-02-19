/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 * @author rosalizaracho
 *
 */
@Entity
public class Seller extends Account {
	List<Product> productList = new ArrayList<Product>();
	Double balance;
}
