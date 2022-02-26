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
@Table(name = "purchase_detail")
public class PurchaseDetail
{
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("productId")
	private Product product;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("purchaseId")
	private Purchase purchase;

	@Column(name = "unit_sale_price")
	private BigDecimal unitSalePrice;

	@Column(name = "total_sale_price")
	private BigDecimal totalSalePrice;

	@Column(name = "modification_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "creation_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
}
