package ar.com.plug.examen.app.entity;

import ar.com.plug.examen.app.common.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@Table(name = "purchases", indexes = {
        @Index(name = "receiptId_item", columnList = "receipt_id, item", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "receipt_id", nullable = false)
    @Range(min = 1L, max = 999999999999L)
    private Long receiptId;

    @Column(name = "item", nullable = false)
    private Integer item;

    @Column(name = "client_document", nullable = false)
    @Range(min = 1, max = 99999999, message = "client_document " + ValidationMessages.DOCUMENT_NUMBER_VALID_VALUES)
    private Integer clientDocument;

    @Column(name = "product_code", nullable = false)
    @Size(min = 1, max = 8, message = "product_code " + ValidationMessages.SIZE_MIN_AND_MAX)
    private String productCode;

    @Column(name = "purchase_quantity", nullable = false)
    @Range(min = 1, max = 99999999, message = "purchase_quantity " + ValidationMessages.DOCUMENT_NUMBER_VALID_VALUES)
    private Integer purchaseQuantity;

    @Column(name = "product_price", nullable = false)
    @Range(min = 0, max = 99999999, message = "product_price " + ValidationMessages.RANGE_MIN_AND_MAX)
    private Double productPrice;

    @Column(name = "seller_id", nullable = false)
    @Range(min = 1L, max = 99999999L)
    private Long sellerId;

    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    @Column(name = "status", nullable = false)
    @Size(max = 10)
    private String status;

}
