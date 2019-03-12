package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {

    Long create(Product product);

    Optional<Product> findProductById(Long id);

    BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount);
}
