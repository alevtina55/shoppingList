package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {
    Long insert(Product product);

    Optional<Product> findById(Long id);

    boolean existsByName(String name);

    void delete(Product product);

    void update(Product product);
}
