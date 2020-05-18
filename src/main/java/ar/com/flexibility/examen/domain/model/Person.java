package ar.com.flexibility.examen.domain.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * Class representing a business person
 *
 */
@Data
@MappedSuperclass
public abstract class Person extends BaseObject{

	private static final long serialVersionUID = -7599988650149962166L;

	private String firstName;
	private String lastName;

}
