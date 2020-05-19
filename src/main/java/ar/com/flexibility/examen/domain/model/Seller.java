package ar.com.flexibility.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "sellers")
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(required = false, hidden = true)
	private Long id;

	private String sellerData;

}
