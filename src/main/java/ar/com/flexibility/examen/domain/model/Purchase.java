package ar.com.flexibility.examen.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.app.api.PurchaseItemApi;

@Entity
@Table(name="purchases")
public class Purchase
{
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="purchase_id", unique=true, nullable=false)
    private Long id;
    
    @Column(name="client", nullable=false)
    private Long clientId;
    
    @Column(name="seller", nullable=false)
    private Long sellerId;
    
    @Embedded
    private ArrayList<PurchaseItem> items;
    
    @Column(name="approved", nullable=false)
    private Boolean approved;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        if(id != null) 
        {
            this.id = id;
        }
    }

    public Long getClientId()
    {
        return clientId;
    }

    public void setClientId(Long clientId)
    {
        if(clientId != null) 
        {
            this.clientId = clientId;
        }
    }

    public Long getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(Long sellerId)
    {
        if(sellerId != null) 
        {
            this.sellerId = sellerId;
        }
    }

    public ArrayList<PurchaseItem> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<PurchaseItem> items)
    {
        if(items != null) 
        {
            for (PurchaseItem item : items)
            {
                this.items.add(item);
            }
        }
    }

    public Boolean isApproved()
    {
        return approved;
    }

    public void setApproved(Boolean approved)
    {
        this.approved = approved;
    }

    
        public void setItemsFromApi(List<PurchaseItemApi> itemsApi)
        {
            
            for(PurchaseItemApi itemApi : itemsApi)
            {
                PurchaseItem item = new PurchaseItem( Long.parseLong(itemApi.getProductId()) , Long.parseLong(itemApi.getQuantity()));
                this.items.add(item);
            }
            
        }
    
    public Purchase(Long id, Long clientId, Long sellerId,
            ArrayList<PurchaseItem> items, Boolean approved)
    {
        super();
        this.id = id;
        this.clientId = clientId;
        this.sellerId = sellerId;
        this.items = items;
        this.approved = approved;
    }

    public Purchase()
    {
        super();
    }

    public Purchase(PurchaseApi purchaseApi)
    {
        super();
        if(purchaseApi.getId() != null) 
        {
            this.id = Long.parseLong(purchaseApi.getId());  
        }
        if(purchaseApi.getClientId() != null) 
        {
            this.clientId = Long.parseLong(purchaseApi.getClientId());
        }
        if(purchaseApi.getSellerId() != null) 
        {
            this.sellerId = Long.parseLong(purchaseApi.getSellerId());  
        }
        if(purchaseApi.getItems() != null) 
        {
            this.setItemsFromApi(purchaseApi.getItems());  
        }
        
        this.approved = purchaseApi.isApproved() == true ? true : false;
    }

 

    @Override
    public String toString()
    {
        return "Purchase [id=" + id + ", clientId=" + clientId + ", sellerId="
                + sellerId + ", items=" + items + ", approved=" + approved
                + "]";
    }

    

    
  
    
    
}
