package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;


public class Client {
	@Id
	@Column
	private Long id;
	@Column
	private String name;
	
	public Client(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
