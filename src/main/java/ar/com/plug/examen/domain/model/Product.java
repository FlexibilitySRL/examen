package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@NotNull
	@Positive
	private long id;

	@Column
	@JsonProperty(required=true)
	@NotNull
	private String name;

	@Column
	private String description;

	@Column(columnDefinition = "double")
	@JsonProperty(required=true)
	@NotNull
	private Double price;

	@Column(columnDefinition = "bigint")
	@JsonProperty(required=true)
	@NotNull
	private int quantity;

}
