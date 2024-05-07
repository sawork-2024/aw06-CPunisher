package com.example.webpos.web;

import org.openapitools.model.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.service.CounterService;

@RestController
public class CounterResource {

    @Autowired
    private CounterService counterService;

    @PostMapping("/counter/checkout")
    public ResponseEntity<Double> checkout(CartDto cartDto) {
        return ResponseEntity.ok(counterService.checkout(cartDto));
    }
}
