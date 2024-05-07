package com.example.webpos.service;

import java.util.Collection;

import com.example.webpos.model.Product;

public interface ProductService {

    public Collection<Product> products();

    public Product getProduct(String id);
}
