package ar.com.flexibility.examen.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Class representing a Seller entity
 */
@Data
@Entity(name  = "Seller")
@EqualsAndHashCode(callSuper = true)
public class Seller extends Person {

	private static final long serialVersionUID = 8087023823720925845L;
}
