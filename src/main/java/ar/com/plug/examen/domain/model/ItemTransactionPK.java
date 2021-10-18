package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemTransactionPK implements Serializable {

    @Column(name = "id_transaccion")
    private Long transactionId;

    @Column(name = "id_producto")
    private Long  productId;


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
