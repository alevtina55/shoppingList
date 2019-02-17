package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public interface ProductService {

    Long create(Product product);

    Product findProductById(Long id);

    BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount);
}
