package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;


public interface ProductValidationRule {
    void validate(ProductDTO productDTO);

    default void checkNotNull(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ProductValidationException("Product must be not null");
        }
    }
}
