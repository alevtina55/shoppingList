package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

public class ProductNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must be not null");
        }
        if (product.getName().trim().length() < 3) {
            throw new ProductValidationException("Name is too short");
        }
        if (product.getName().trim().length() > 32) {
            throw new ProductValidationException("Name is to long");
        }
    }
}
