package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Cart;

public interface CartService {
    public Cart addProductToCart(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException;

    public Cart removeProductFromCart(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException;

    public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException;

    public Cart increaseProductQuantity(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException;

    public Cart decreaseProductQuantity(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException;
}
