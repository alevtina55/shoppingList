package com.javaguru.shoppinglist.converters;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;

import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setActualPrice(productDTO.getActualPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setId(productDTO.getId());
        product.setCategory(productDTO.getCategory());

        return product;
    }

    public ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(product.getDescription());
        productDTO.setActualPrice(product.getActualPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        productDTO.setCategory(product.getCategory());

        return productDTO;
    }
}
