package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> products = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        return products.get(id);
    }

    public Long insert(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot be null");
        }

        if (nameExists(product.getName())) {
            throw new IllegalArgumentException("Product name is not unique");
        }

        product.setId(productIdSequence);

        products.put(productIdSequence, product);
        return productIdSequence++;
    }

    private boolean nameExists(String name) {
        for (Product product : products.values()) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
