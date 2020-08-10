package ar.com.flexibility.examen.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

    @Basic
    @Column (name = "identifier", nullable = false, length = 50)
	private String identifier;
    @Basic
    @Column (name = "name", nullable = false, length = 150)
	private String name;
    @Basic
    @Column (name = "surname", nullable = false, length = 150)
	private String surname;
    
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
