package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "name cannot be blank")
	@Column(unique = true)
	private String name;

	@NotBlank(message = "description cannot be blank")
	private String description;

	@NotNull(message = "price cannot be null")
	private BigDecimal price;

	@NotNull(message = "stock cannot be null")
	@Min(0)
	private Integer stock;

}
