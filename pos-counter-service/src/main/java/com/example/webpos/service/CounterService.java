package com.example.webpos.service;

import org.openapitools.model.CartDto;

public interface CounterService {
    double checkout(CartDto cart);
}
