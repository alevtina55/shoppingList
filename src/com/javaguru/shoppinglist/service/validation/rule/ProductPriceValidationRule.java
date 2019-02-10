package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        BigDecimal price = product.getPrice();
        if (price == null) {
            throw new ProductValidationException("Product price must be not null");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductValidationException("Price should be greater than 0");
        }
    }
}
