package ar.com.plug.examen.app.mapper;

import ar.com.plug.examen.app.DTO.CartDTO;
import ar.com.plug.examen.app.DTO.PurchaseDTO;
import ar.com.plug.examen.app.api.CartApi;
import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    final CustomerMapper customerMapper;
    final PurchaseMapper purchaseMapper;
    final ProductRepository productRepository;

    /**
     * @param source
     * @return
     */
    public CartDTO asDTO(CartApi source) {
        List<CartDTO.CartItemDTO> items = source.getItems().stream()
                .map(this::asItemDTO)
                .collect(Collectors.toList());
        PurchaseDTO purchaseDTO = (source.getPurchase() != null) ? purchaseMapper.asDTO(source.getPurchase()) : null;
        return new CartDTO(null, items, customerMapper.asDTO(source.getCustomer()), purchaseDTO);
    }

    /**
     * @param source
     * @return
     */
    public CartDTO asDTO(Cart source) {
        List<CartDTO.CartItemDTO> items = source.getItems().stream()
                .map(this::asItemDTO)
                .toList();
        PurchaseDTO purchaseDTO = (source.getPurchase() != null) ? purchaseMapper.asDTO(source.getPurchase()) : null;
        return new CartDTO(source.getId(), items, customerMapper.asDTO(source.getCustomer()), purchaseDTO);

    }

    /**
     * @param source
     * @return
     */
    public CartDTO.CartItemDTO asItemDTO(CartApi.CartItemApi source) {
        Product product = productRepository.findById(source.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        return new CartDTO.CartItemDTO(product, source.getQuantityApi());
    }

    /**
     * @param source
     * @return
     */
    public CartDTO.CartItemDTO asItemDTO(Cart.CartItem source) {
        return new CartDTO.CartItemDTO(source.getProduct(), source.getQuantity());
    }
}

