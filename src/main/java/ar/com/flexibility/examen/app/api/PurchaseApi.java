package ar.com.flexibility.examen.app.api;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;

@JsonRootName(value = "purchase") // The @JsonRootName annotation is used – if
                                 // wrapping is enabled – to specify the name of
                                 // the root wrapper to be used.
@JsonIgnoreProperties(ignoreUnknown = false)
public class PurchaseApi
{
     
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("clientId")
    private String clientId;
    
    @JsonProperty("sellerId")
    private String sellerId;
    
    @JsonProperty("items")
    private List<PurchaseItemApi> items;
    
    @JsonProperty("approved")
    private Boolean approved = false;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(String sellerId)
    {
        this.sellerId = sellerId;
    }

    public List<PurchaseItemApi> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<PurchaseItemApi> items)
    {
        if(items != null) 
        {
            for (PurchaseItemApi item : items)
            {
                this.items.add(item);
            }
        }
    }

    public Boolean isApproved()
    {
        return approved;
    }

    public void notApproved()
    {
        this.approved = false;
    }
    
    public void approved()
    {
        this.approved = true;
    }

    
    public void setItemsFromNotApi(ArrayList<PurchaseItem> items)
    {
        
        for(PurchaseItem item : items)
        {
            PurchaseItemApi itemApi = new PurchaseItemApi(item.getProductId() + "", item.getQuantity() + "");
            this.items.add(itemApi);
        }
        
    }
    public PurchaseApi(String id, String clientId, String sellerId,
            ArrayList<PurchaseItemApi> items, Boolean approved)
    {
        super();
        this.id = id;
        this.clientId = clientId;
        this.sellerId = sellerId;
        this.items = items;
        this.approved = approved;
    }

    public PurchaseApi()
    {
        super();
    }

    public PurchaseApi(Purchase purchase)
    {
        super();
        
        if(purchase.getId() != null)
        {
            this.id = purchase.getId() + "";
        }
        if(purchase.getClientId() != null)
        {
            this.clientId = purchase.getClientId() + "";
        }
        if(purchase.getSellerId() != null)
        {
            this.sellerId = purchase.getSellerId() + "";
        }

        if(purchase.getItems() != null) 
        {
            this.setItemsFromNotApi(purchase.getItems());  
        }
        
        if(purchase.isApproved())
        {
            this.approved = true;
        }
        
    }

    @Override
    public String toString()
    {
        return "PurchaseApi [id=" + id + ", clientId=" + clientId
                + ", sellerId=" + sellerId + ", items=" + items + ", approved="
                + approved + "]";
    }
    
    
    
}
