package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findById(Long id) {
        return products.get(id);
    }

    public Long insert(Product product) {
        product.setId(productIdSequence);

        products.put(productIdSequence, product);
        return productIdSequence++;
    }

    public boolean existsByName(String name) {
        return products.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }
}
