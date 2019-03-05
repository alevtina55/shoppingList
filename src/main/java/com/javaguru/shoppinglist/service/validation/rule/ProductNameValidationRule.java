package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {
    private static final int MIN_LENGTH_VALUE = 3;
    private static final int MAX_LENGTH_VALUE = 32;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        String name = product.getName();
        checkIfNameNotNull(name);
        checkNameMinLength(name);
        checkNameMaxLength(name);
    }

    private void checkIfNameNotNull(String name) {
        if (name == null) {
            throw new ProductValidationException("Product name must be not null");
        }
    }

    private void checkNameMinLength(String name) {
        if (name.trim().length() < MIN_LENGTH_VALUE) {
            throw new ProductValidationException("Name is too short");
        }
    }

    private void checkNameMaxLength(String name) {
        if (name.trim().length() > MAX_LENGTH_VALUE) {
            throw new ProductValidationException("Name is to long");
        }
    }
}
