package ar.com.plug.examen.app.entity;

import ar.com.plug.examen.app.common.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_code", unique = true, nullable = false)
    @Size(min = 1, max = 8, message = "product_code " + ValidationMessages.SIZE_MIN_AND_MAX)
    private String productCode;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 30, message = "product_name " + ValidationMessages.SIZE_MIN_AND_MAX)
    private String productName;

    @Column(name = "description", nullable = false)
    @Size(min = 1, max = 50, message = "product_description" + ValidationMessages.SIZE_MIN_AND_MAX)
    private String productDescription;

    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 99999999, message = "product_price " + ValidationMessages.RANGE_MIN_AND_MAX)
    private Double productPrice;

}
