package com.example.webpos.web;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.api.ProductsApi;
import com.example.webpos.mapper.ProductMapper;
import com.example.webpos.service.ProductService;

@RestController
public class ProductResource implements ProductsApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper mapper;

    @Override
    public ResponseEntity<List<ProductDto>> listProducts() {
        return ResponseEntity.ok(new ArrayList<>(mapper.toProductsDto(productService.products())));
    }

    @Override
    public ResponseEntity<ProductDto> showProductById(String productId) {
        return ResponseEntity.ok(mapper.toProductDto(productService.getProduct(productId)));
    }
}
