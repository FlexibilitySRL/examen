package ar.com.flexibility.examen.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class for items into a {@link PurchaseOrder}
 */
@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderItem extends BaseObject {

	private static final long serialVersionUID = -5841573779245581148L;

	@ManyToOne
	private Product product;

	private Integer quantity;

}
