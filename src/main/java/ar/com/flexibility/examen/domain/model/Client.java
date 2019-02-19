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
public class Client extends Account{
	List<Purchase> purchaseList = new ArrayList<Purchase>();
	Double balance; 
	
}
