package com.example.webpos.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.openapitools.model.ProductDto;

import com.example.webpos.model.Product;

@Mapper
public interface ProductMapper {

    Collection<ProductDto> toProductsDto(Collection<Product> products);

    Collection<Product> toProducts(Collection<ProductDto> products);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product pet);
}
