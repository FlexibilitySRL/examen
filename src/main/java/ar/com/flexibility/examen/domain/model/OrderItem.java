package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@NotNull
	@Min(1)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
