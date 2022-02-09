package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.OrderStatusEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	private Seller seller;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;

	private OrderStatusEnum status;

	@NotNull
	private Date creationDate;
}
