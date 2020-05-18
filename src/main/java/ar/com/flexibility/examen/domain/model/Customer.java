package ar.com.flexibility.examen.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Class representing a customer entity
 *
 */
@Data
@Entity(name = "Customer")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Customer extends Person{

	private static final long serialVersionUID = 7882510107833625403L;

	private String address;
	private String zipCode;
	private String email;
	private String phoneNumber;
}
