package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Product {
	@Id
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	
	public Product(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
