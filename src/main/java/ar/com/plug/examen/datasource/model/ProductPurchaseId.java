package ar.com.plug.examen.datasource.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class ProductPurchaseId implements Serializable {

    @Column(name = "product_id")
    private long productId;

    @Column(name = "purchase_id")
    private long purchaseId;

}
