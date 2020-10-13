package ar.com.plug.examen.domain.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@Table(name = "persons")
@DiscriminatorColumn(name="person_type",discriminatorType=DiscriminatorType.STRING)  
public abstract class Person extends AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	protected String surname;

	protected String email;
	
	public Person() {
	}
	
	public Person(Long id, String name, String surname, String email) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return getName() + " " + getSurname();
	}
	
}