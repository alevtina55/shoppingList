package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Long create(ProductDTO productDTO);

    ProductDTO findProductById(Long id);

    void deleteProductById(Long id);

    BigDecimal calculateActualPrice(BigDecimal price, BigDecimal discount);

    void update(ProductDTO productDTO);

    List<ProductDTO> findAll();

    ProductDTO findProductByName(String name);
}