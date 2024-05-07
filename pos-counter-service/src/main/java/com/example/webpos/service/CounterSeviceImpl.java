package com.example.webpos.service;

import org.openapitools.model.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CounterSeviceImpl implements CounterService {

    @Override
    public double checkout(CartDto cart) {
        double sum = 0;
        for (var item : cart.getItems()) {
            sum += item.getProduct().getPrice() * item.getQuantity();
        }
        return sum;
    }
}
