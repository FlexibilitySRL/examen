package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@JsonProperty(required=true)
	private String name;

	@Column
	private String description;

	@Column(columnDefinition = "double")
	@JsonProperty(required=true)
	private Double price;

	@Column(columnDefinition = "bigint")
	@JsonProperty(required=true)
	private int quantity;

}
