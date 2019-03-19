package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("dev")
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long productIdSequence = 0L;

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
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
