package ar.com.plug.examen.app.DTO;


import ar.com.plug.examen.domain.model.Product;

import java.util.List;
import java.util.UUID;

public record CartDTO(UUID id, List<CartItemDTO> items, CustomerDTO customer, PurchaseDTO purchase) {
    public record CartItemDTO(Product product, Integer quantity) {
    }
}
