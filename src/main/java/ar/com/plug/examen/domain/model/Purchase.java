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
@Table(name = "purchase")
public class Purchase
{
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "receipt_number", updatable = false, nullable = false)
	private String receiptNumber;

	@Column(name = "total_amount")
	private BigDecimal total;

	@Column(name = "approve", nullable = false)
	private Boolean approve;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
	private Client client;

	@Column(name = "modification_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "creation_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
}
