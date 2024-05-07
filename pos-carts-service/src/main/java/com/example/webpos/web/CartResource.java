package com.example.webpos.web;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.model.CartDto;
import org.openapitools.model.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.api.CartsApi;
import com.example.webpos.mapper.CartMapper;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.service.CartService;

import jakarta.validation.Valid;

@RestController
public class CartResource implements CartsApi {

    @Autowired
    private CartService cartService;

    @Override
    public ResponseEntity<CartDto> addCart(@Valid CartDto cartDto) {
        Cart cart = CartMapper.INSTANCE.toCart(cartDto);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Cart resCart = cartService.addEmptyCart(cart);
        if (resCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> addItemToCart(Integer cartId, @Valid CartItemDto cartItemDto) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Item item = CartMapper.INSTANCE.toCartItem(cartItemDto);
        Cart resCart = cartService.addItemToCart(cart, item);
        if (resCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CartDto resCartDto = CartMapper.INSTANCE.toCartDto(resCart);
        return new ResponseEntity<>(resCartDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> getCartById(Integer cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CartDto cartDto = CartMapper.INSTANCE.toCartDto(cart);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getCartTotalAmount(Integer cartId) {
        double totalAmount = cartService.checkout(cartId);
        if (cartId == -1d)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(totalAmount);
    }

    @Override
    public ResponseEntity<List<CartDto>> listCarts() {
        List<CartDto> carts = new ArrayList<>(CartMapper.INSTANCE.toCartDtos(cartService.getAllCarts()));
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
