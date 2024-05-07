package com.example.webpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webpos.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
