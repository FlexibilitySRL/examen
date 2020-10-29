package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Este recurso representa a un cliente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	private Long id;
	private String name;
	private String color;
	private String thumbnail;

	public Product id(Long id) {
		this.id = id;
		return this;
	}
}