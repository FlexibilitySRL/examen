package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.CartRepository;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addProductToCart(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty())
            throw new CustomerException("Customer not found!");

        Optional<Product> itemOpt = productRepository.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");

        Customer customer = opt.get();
        Cart cart = customer.getCart();

        List<Product> itemList = cart.getProducts();
        boolean flag = true;
        for (int i = 0; i < itemList.size(); i++) {
            Product element = itemList.get(i);
            if (element.getProductId() == productId) {
                if (cart.getProductQuantity() == null) {
                    cart.setProductQuantity(1);

                } else {
                    cart.setProductQuantity(cart.getProductQuantity() + 1);
                }

                flag = false;
            }
        }
        if (flag) {
            cart.getProducts().add(itemOpt.get());
        }

        cartRepository.save(cart);
        return cart;

    }

    @Override
    public Cart removeProductFromCart(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty())
            throw new CustomerException("Customer not found!");

        Optional<Product> itemOpt = productRepository.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");
        Customer customer = opt.get();
        Cart cart = customer.getCart();
        List<Product> itemList = cart.getProducts();
        boolean flag = false;
        for (int i = 0; i < itemList.size(); i++) {
            Product element = itemList.get(i);
            if (element.getProductId() == productId) {
                itemList.remove(element);
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new CartException("Product not removed from cart");
        }
        cart.setProducts(itemList);
        cartRepository.save(cart);
        return cart;

    }

    @Override
    public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty())
            throw new CustomerException("Customer not found!");
        Cart c = opt.get().getCart();
        if (c == null) {
            throw new CartException("cart not found");
        }
        c.getProducts().clear();
        return cartRepository.save(c);

    }

    @Override
    public Cart increaseProductQuantity(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty())
            throw new CustomerException("Customer not found!");

        Optional<Product> itemOpt = productRepository.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");

        Customer customer = opt.get();
        Cart cart = customer.getCart();
        List<Product> itemList = cart.getProducts();
        boolean flag = true;
        for (int i = 0; i < itemList.size(); i++) {
            Product element = itemList.get(i);
            if (element.getProductId() == productId) {
                cart.setProductQuantity(cart.getProductQuantity() + 1);
                flag = false;
            }
        }
        if (flag) {
            cart.getProducts().add(itemOpt.get());
        }

        cartRepository.save(cart);
        return cart;

    }

    @Override
    public Cart decreaseProductQuantity(Integer customerId, Integer productId)
            throws CartException, CustomerException, ProductException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty())
            throw new CustomerException("Customer not found!");

        Optional<Product> itemOpt = productRepository.findById(productId);
        if (itemOpt.isEmpty())
            throw new ProductException("Product not found!");

        Customer customer = opt.get();
        Cart cart = customer.getCart();
        List<Product> itemList = cart.getProducts();
        boolean flag = true;
        if (itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                Product element = itemList.get(i);
                if (element.getProductId() == productId) {
                    cart.setProductQuantity(cart.getProductQuantity() + 1);
                    flag = false;
                }
            }
        }

        if (flag) {
            cart.getProducts().add(itemOpt.get());
        }

        cartRepository.save(cart);
        return cart;
    }
}
