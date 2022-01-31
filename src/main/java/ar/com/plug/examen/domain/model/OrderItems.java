package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderItems
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//	@JoinColumn(name = "product_id", referencedColumnName = "id")
//	private Product product;
//
//	private Long quantity;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "order_id", referencedColumnName = "id")
//	private Order order;

}
