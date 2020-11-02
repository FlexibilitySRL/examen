package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ar.com.plug.generated.model.PurchaseTransaction.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Este objeto representa a una transacción de compra
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String approverId;
	private LocalDateTime approvalDateTime;
	private LocalDateTime rejectionDateTime;
	private LocalDateTime createDateTime;
	private StatusEnum status;
	private String purchaseOrderId;

	/**
	 * Gets or Sets status
	 */
	public enum StatusEnum {
		PENDING("PENDING"),

		APPROVED("APPROVED"),

		REJECTED("REJECTED");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StatusEnum fromValue(String value) {
			for (StatusEnum b : StatusEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

}
