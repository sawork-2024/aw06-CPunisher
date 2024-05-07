package com.example.webpos.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.webpos.model.Product;
import com.example.webpos.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<Product> products() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.getReferenceById(id);
    }
}
