package ar.com.flexibility.examen.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
@EqualsAndHashCode(exclude = { "purchases" })
@ToString(exclude = { "purchases" })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(required = false, hidden = true)
	private Long id;

	private String productData;

	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private Set<Purchase> purchases = new HashSet<Purchase>();

}
