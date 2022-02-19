package ar.com.plug.examen.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product
{
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sku", nullable = false)
	private String sku;

	@Column(name = "sku_vendor", nullable = false)
	private String skuVendor;

	@Column(name = "cost")
	private BigDecimal cost;

	@Column(name = "sale_price")
	private BigDecimal salePrice;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
	private Seller seller;

	@Column(name = "stock_quantity", nullable = false)
	private Integer stockQty;

	@Column(name = "modification_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "creation_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
}
