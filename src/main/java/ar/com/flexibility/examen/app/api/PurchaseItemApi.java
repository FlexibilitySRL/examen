package ar.com.flexibility.examen.app.api;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;

@JsonRootName(value = "items") // The @JsonRootName annotation is used – if
                                 // wrapping is enabled – to specify the name of
                                 // the root wrapper to be used.
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonInclude(Include.NON_EMPTY)
public class PurchaseItemApi
{
     
    @JsonProperty("quantity")
    private String quantity;
    
    @JsonProperty("productId")
    private String productId;
        

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public PurchaseItemApi(String productId, String quantity)
    {
        super();
        this.quantity = quantity;
        this.productId = productId;
    }

    public PurchaseItemApi()
    {
        super();
    }

    @Override
    public String toString()
    {
        return "PurchaseItemApi [quantity=" + quantity + ", productId="
                + productId + "]";
    }
    
    

 
    
    
}
