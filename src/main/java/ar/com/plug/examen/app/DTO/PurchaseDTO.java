package ar.com.plug.examen.app.DTO;

import ar.com.plug.examen.domain.model.Purchase;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseDTO(UUID id, CustomerDTO customer, VendorDTO vendor, Purchase.PurchaseStatus status,
                          BigDecimal price) {
}
