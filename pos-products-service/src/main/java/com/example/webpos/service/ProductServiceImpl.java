package com.example.webpos.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import com.example.webpos.model.Product;
import com.example.webpos.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Override
    public Collection<Product> products() {
        CircuitBreaker circuitBreaker = cbFactory.create("circuitbreaker");

        return circuitBreaker.run(() -> productRepository.findAll(), throwable -> List.of());
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.getReferenceById(id);
    }
}
