package com.example.webpos.service;

import java.util.Collection;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;

public interface CartService {
    Cart addItemToCart(Cart cart, Item item);

    double checkout(Integer cartId);

    Cart addEmptyCart(Cart cart);

    Collection<Cart> getAllCarts();

    Cart getCartById(Integer id);
}
