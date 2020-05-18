package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class representing a purchase order
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrder extends BaseObject {

	private static final long serialVersionUID = -2095365310866896345L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "purchaseorder_items_jt")

	private List<PurchaseOrderItem> purchaseOrderItems;

	private PurchaseOrderStatus orderStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date resolutionDate;

	@OneToOne
	private Seller seller;

	@OneToOne
	private Customer customer;

}
