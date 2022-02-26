package ar.com.plug.examen.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "product")
public class Product
{
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sku", nullable = false, unique = true)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("sellerId")
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
