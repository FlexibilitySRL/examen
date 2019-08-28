package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = -8463036589235511209L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "country cannot be null")
	private String country;

	@NotBlank(message = "province cannot be null")
	private String province;

	@NotBlank(message = "city cannot be null")
	private String city;

	@NotBlank(message = "direction cannot be null")
	private String direction;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

	public Address(String country, String province, String city, String direction) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.direction = direction;
	}
}
