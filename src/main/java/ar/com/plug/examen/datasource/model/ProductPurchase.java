package ar.com.plug.examen.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties("purchase")
public class ProductPurchase {

    @EmbeddedId
    private ProductPurchaseId id;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("purchaseId")
    private Purchase purchase;
}
