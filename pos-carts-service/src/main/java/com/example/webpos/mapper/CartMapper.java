package com.example.webpos.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.CartDto;
import org.openapitools.model.CartItemDto;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;

@Mapper(uses = ProductMapper.class)
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Collection<Cart> toCarts(Collection<CartDto> cartDtos);

    Collection<CartDto> toCartDtos(Collection<Cart> carts);

    Item toCartItem(CartItemDto cartItemDto);

    CartItemDto toCartItemDto(Item cartItem);

    Cart toCart(CartDto cartDto);

    CartDto toCartDto(Cart cart);
}