package com.example.webpos.service;

import java.util.Collection;
import java.util.Optional;

import org.openapitools.model.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.webpos.mapper.CartMapper;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.repository.CartRepository;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private CartMapper mapper;

    private static final String COUNTER_URL = "http://COUNTER-SERVICE/counter";

    @Override
    public double checkout(Integer cartId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartDto> request = new HttpEntity<>(mapper.toCartDto(cartRepository.findById(cartId).get()),
                headers);
        return restTemplate.postForObject(COUNTER_URL + "/checkout", request, Double.class);
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
