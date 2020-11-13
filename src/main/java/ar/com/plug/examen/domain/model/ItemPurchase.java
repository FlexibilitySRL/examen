package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.InvalidProductIdException;
import ar.com.plug.examen.domain.exceptions.InvalidQuantityException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Class to represent an item purchase
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name = "item_purchase")
@ApiModel("Model item purchase")
public class ItemPurchase {
    public ItemPurchase(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "the item purchase's id", required = true)
    Long id;

    @Column(name = "quantity")
    @ApiModelProperty(value = "the item purchase's quantity", required = true)
    Long quantity;

    @Column(name = "product_id")
    @ApiModelProperty(value = "the item purchase's productId", required = true)
    Long productId;

    public ItemPurchase(Long quantity, Long productId) throws InvalidQuantityException, InvalidProductIdException {
        this.validateQuantity(quantity);
        this.validateProductId(productId);
    }

    private void validateProductId(Long productId) throws InvalidProductIdException {
        if(productId<=0){
            throw new InvalidProductIdException("The product id must be higher than 0.");
        }
        this.productId = productId;
    }

    private void validateQuantity(Long quantity) throws InvalidQuantityException {
        if(quantity<=0){
            throw new InvalidQuantityException("The quantity must be higher than 0.");
        }
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }
}
