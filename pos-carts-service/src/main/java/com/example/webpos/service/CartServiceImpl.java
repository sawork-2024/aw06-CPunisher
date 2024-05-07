package com.example.webpos.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.repository.CartRepository;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public double checkout(Integer cartId) {
        Optional<Cart> cart = this.cartRepository.findById(cartId);
        if (cart.isEmpty())
            return -1.0;
        Cart realCart = cart.get();
        double sum = 0;
        for (Item item : realCart.getItems()) {
            sum += item.getProduct().getPrice() * item.getQuantity();
        }
        return sum;
    }

    @Override
    public Collection<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addEmptyCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Integer id) {
        Optional<Cart> cart = this.cartRepository.findById(id);
        if (cart.isEmpty())
            return null;
        else
            return cart.get();
    }

    @Override
    public Cart addItemToCart(Cart cart, Item item) {
        if (cart.addItem(item))
            return cartRepository.save(cart);
        return null;
    }
}
