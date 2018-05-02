package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurchaseItem   
{
    @Column(name="productId", nullable=false)
    Long productId;
    
    @Column(name="quantity", nullable=false)
    Long quantity;
    
    public Long getProductId()
    {
        return productId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    public Long getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }
    public PurchaseItem(Long productId, Long quantity)
    {
        super();
        this.productId = productId;
        this.quantity = quantity;
    }
    public PurchaseItem()
    {
        super();
    }
    @Override
    public String toString()
    {
        return "PurchaseItem [productId=" + productId + ", quantity=" + quantity
                + "]";
    }
    
    
}
