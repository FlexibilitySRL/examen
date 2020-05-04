package ar.com.flexibility.examen.app.dto;

public class OrderApprovalDTO {

    private Long orderId;

    private boolean approved;

    public OrderApprovalDTO() { }

    public OrderApprovalDTO(Long orderId, boolean approved) {
        this.orderId = orderId;
        this.approved = approved;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
