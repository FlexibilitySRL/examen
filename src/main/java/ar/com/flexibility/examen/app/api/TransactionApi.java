package ar.com.flexibility.examen.app.api;

import java.time.ZonedDateTime;

public class TransactionApi {

    private Long productId;
    private Long sellerId;
    private ZonedDateTime date;
    private Double price;
    private String status;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionApi{" +
                ", productId=" + productId +
                ", sellerId=" + sellerId +
                ", date=" + date +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
