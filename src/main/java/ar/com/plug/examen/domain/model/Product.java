package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@JsonProperty(required = true)
	@NotNull
	@Positive
	private long id;

	@Column
	@JsonProperty(required = true)
	@NotEmpty
	private String name;

	@Column
	private String description;

	@Column(columnDefinition = "double")
	@JsonProperty(required = true)
	@Positive
	@NotNull
	private Double price;

	@Column(columnDefinition = "bigint")
	@Positive
	@NotNull
	@JsonProperty(value="quantity",required = true)
	@JsonAlias(value="stock")
	private int quantity;

}
