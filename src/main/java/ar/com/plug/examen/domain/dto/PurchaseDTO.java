package ar.com.plug.examen.domain.dto;

import ar.com.plug.examen.domain.enums.PurchaseState;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class PurchaseDTO {
    private long idPurchase;
    private Customer customer;
    private Seller seller;
    private Product product;
    private String voucher;
    private LocalDateTime datePurchase;
    private BigDecimal taxes;
    private BigDecimal amount;
    private String state;


    public static PurchaseDTO fromModelToDto(
            Purchase purchase) {
        return PurchaseDTO.builder()
                .idPurchase(purchase.getIdPurchase())
                .voucher(purchase.getVoucher())
                .taxes(purchase.getTaxes())
                .amount(purchase.getAmount())
                .datePurchase(purchase.getDatePurchase())
                .state(String.valueOf(purchase.getUpdateDate() == null ? PurchaseState.SOLICITADA: PurchaseState.APROBADA))
                .build();
    }
}
