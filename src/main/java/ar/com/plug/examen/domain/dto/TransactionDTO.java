package ar.com.plug.examen.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionDTO {

    private Long transantionId;
    private String customerId;
    private String customerName;
    private String customerLastName;
    private Long sellerId;
    private String sellerName;
    private String sellerLastName;
    private LocalDateTime date;
    private String state;

    private List<ItemTransactionDTO> itemsTransaction;

    public TransactionDTO() {
    }

    public Long getTransantionId() {
        return transantionId;
    }

    public void setTransantionId(Long transantionId) {
        this.transantionId = transantionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ItemTransactionDTO> getItemsTransaction() {
        return itemsTransaction;
    }

    public void setItemsTransaction(List<ItemTransactionDTO> itemsTransaction) {
        this.itemsTransaction = itemsTransaction;
    }
}
